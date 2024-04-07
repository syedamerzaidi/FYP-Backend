package com.fyp.hca.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.topic}")
    private String topic; // Using a fixed topic name defined in application.properties

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message) {
        // PRINT MESSAGE
        System.out.println("Sending message: " + message + " to topic: " + topic);
        try {
            kafkaTemplate.send(topic, message);
            System.out.println("Message sent successfully");
        } catch (Exception e) {
            System.err.println("Error sending message: " + e.getMessage());
        }
    }
}
