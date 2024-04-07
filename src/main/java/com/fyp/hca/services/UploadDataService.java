package com.fyp.hca.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UploadDataService {

    private final KafkaProducerService kafkaProducerService;

    public UploadDataService(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    public String uploadFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IOException("Please select a file to upload");
        }

        try {
            String originalFileName = file.getOriginalFilename();
            assert originalFileName != null;
            if (!originalFileName.endsWith(".csv")) {
                throw new IOException("Only CSV files are allowed");
            }

            String fileName = UUID.randomUUID() + ".csv";
            String packagePath = "";
            String filePath = "src/main/java/com/fyp/hca/uploaddata/" + fileName;

            Path directory = Paths.get(packagePath);
            if (!Files.exists(directory)) {
                Files.createDirectories(directory);
            }

            // Save file
            byte[] bytes = file.getBytes();
            Path path = Paths.get(filePath);
            Files.write(path, bytes);

            // Parse CSV and publish to Kafka
            List<String> lines = Files.readAllLines(path);
            //print current time in 12 hour format
            System.out.println("Current time in 12 hour format: " + new SimpleDateFormat("hh:mm a").format(new Date()));

            // add a delay of 20 sec
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 1; i < lines.size(); i++) { // Start from the second line (index 1)
                String line = lines.get(i);
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String name = parts[0].trim();
                    int age = Integer.parseInt(parts[1].trim());
                    System.out.println("Name: " + name + ", Age: " + age);
                    kafkaProducerService.sendMessage( name + "," + age);
                }
            }

            return "File uploaded successfully";
        } catch (IOException e) {
            throw new IOException("Failed to upload file");
        }
    }
}
