package com.fyp.hca.services;

import com.fyp.hca.entity.Prediction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.FileSystemResource;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class PredictionService {

    private final RestTemplate restTemplate;
    private final String pythonApiUrl;

    public PredictionService(RestTemplate restTemplate, @Value("${spark.api.url}") String pythonApiUrl) {
        this.restTemplate = restTemplate;
        this.pythonApiUrl = pythonApiUrl;
    }

    public List<Prediction> fetchPredictions(String folderPath) {
        String apiUrl = pythonApiUrl + "/update_predictions?folder=" + folderPath;
        // Adjust this as per your requirement, whether it's fetching from a GET request or performing a POST request
        List<Prediction> predictions = restTemplate.getForObject(apiUrl, List.class);
        return predictions;
    }
    public void uploadDataToKafka(String folderPath, MultipartFile file) throws IOException {
        // Construct the full API URL
        String apiUrl = pythonApiUrl + "/upload";

        // Prepare the request body
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("folder", folderPath);
        body.add("file", file.getResource()); // Use () to get the resource representation

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        try {
            restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, String.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Error uploading file: " + e.getMessage());
        }
    }

    /*public static class Prediction {
        private String date;
        private double predictedPatients;
        private double ventilatorsRequired;
        private double oxygenCylindersRequired;

        // Constructors, getters, and setters
        // (typically generated automatically by IDE or explicitly defined)

        public Prediction(String date, double predictedPatients, double ventilatorsRequired, double oxygenCylindersRequired) {
            this.date = date;
            this.predictedPatients = predictedPatients;
            this.ventilatorsRequired = ventilatorsRequired;
            this.oxygenCylindersRequired = oxygenCylindersRequired;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public double getPredictedPatients() {
            return predictedPatients;
        }

        public void setPredictedPatients(double predictedPatients) {
            this.predictedPatients = predictedPatients;
        }

        public double getVentilatorsRequired() {
            return ventilatorsRequired;
        }

        public void setVentilatorsRequired(double ventilatorsRequired) {
            this.ventilatorsRequired = ventilatorsRequired;
        }

        public double getOxygenCylindersRequired() {
            return oxygenCylindersRequired;
        }

        public void setOxygenCylindersRequired(double oxygenCylindersRequired) {
            this.oxygenCylindersRequired = oxygenCylindersRequired;
        }
    }*/

}
