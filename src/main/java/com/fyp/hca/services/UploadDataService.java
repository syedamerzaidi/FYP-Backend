package com.fyp.hca.services;

import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class UploadDataService {

    private final KafkaProducerService kafkaProducerService;

    @Autowired
    public UploadDataService(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    public String uploadFile(MultipartFile file, int hospitalId, int diseaseId) throws IOException {
        if (file.isEmpty()) {
            throw new IOException("Please select a file to upload");
        }

        if (!file.getOriginalFilename().endsWith(".csv")) {
            throw new IOException("Only CSV files are allowed");
        }

        String fileName = "Hospital-" + hospitalId + "-" + new SimpleDateFormat("dd-MM-yyyy").format(new Date()) + "-patientData.csv";
        Path path = Paths.get("src/main/java/com/fyp/hca/uploaddata/", fileName);

        Files.createDirectories(path.getParent());
        file.transferTo(path);

        try (CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            List<String[]> allRows = reader.readAll();
            processData(allRows, hospitalId, diseaseId);
        } catch (Exception e) {
            throw new IOException("Failed to process data: " + e.getMessage());
        } finally {
            Files.deleteIfExists(path);
        }

        return "File uploaded successfully";
    }

    public void processData(List<String[]> allRows, int hospitalId, int diseaseId) {
        for (int i = 1; i < allRows.size(); i++) {
            String[] parts = allRows.get(i);
            if (parts.length == 21) {
                String message = String.join(",", parts) + "," + hospitalId + "," + diseaseId;
                kafkaProducerService.sendMessage(message);
            }
        }
    }
}