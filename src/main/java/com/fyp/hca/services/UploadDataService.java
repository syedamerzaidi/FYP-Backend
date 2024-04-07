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
            byte[] bytes = file.getBytes();
            Path path = Paths.get(filePath);
            Files.write(path, bytes);

            List<String> lines = Files.readAllLines(path);

            for (int i = 1; i < lines.size(); i++) {
                String line = lines.get(i);
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String firstName = parts[0].trim();
                    String lastName = parts[1].trim();
                    String cnic = parts[2].trim();
                    int age = Integer.parseInt(parts[3].trim());
                    String admissionDateStr = parts[4].trim();
                    String chronicDisease = parts[5].trim();
                    String gender = parts[6].trim();
                    boolean respiratory = Boolean.parseBoolean(parts[7].trim());
                    boolean weaknessPain = Boolean.parseBoolean(parts[8].trim());
                    boolean fever = Boolean.parseBoolean(parts[9].trim());
                    boolean gastrointestinal = Boolean.parseBoolean(parts[10].trim());
                    boolean nausea = Boolean.parseBoolean(parts[11].trim());
                    boolean cardiac = Boolean.parseBoolean(parts[12].trim());
                    boolean highFever = Boolean.parseBoolean(parts[13].trim());
                    boolean kidney = Boolean.parseBoolean(parts[14].trim());
                    boolean asymptomatic = Boolean.parseBoolean(parts[15].trim());
                    boolean diabetes = Boolean.parseBoolean(parts[16].trim());
                    boolean neuro = Boolean.parseBoolean(parts[17].trim());
                    boolean hypertension = Boolean.parseBoolean(parts[18].trim());
                    boolean cancer = Boolean.parseBoolean(parts[19].trim());
                    boolean thyroid = Boolean.parseBoolean(parts[20].trim());
                    String message=firstName+","+lastName+","+cnic+","+age+","+admissionDateStr+","+chronicDisease+gender+
                            respiratory+weaknessPain+fever+gastrointestinal+nausea+cardiac+highFever+kidney+asymptomatic+diabetes+neuro+hypertension+cancer+thyroid;
                    kafkaProducerService.sendMessage(message);
                }
            }

            return "File uploaded successfully";
        } catch (IOException e) {
            throw new IOException("Failed to upload file");
        }
    }
}
