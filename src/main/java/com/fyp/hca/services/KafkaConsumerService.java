package com.fyp.hca.services;

import com.fyp.hca.entity.Person;
import com.fyp.hca.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class KafkaConsumerService {

    private final PersonRepository personRepository;

    public KafkaConsumerService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @KafkaListener(topics = "${kafka.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(String message) {
        // PRINT MESSAGE
        System.out.println("Received message: " + message);
        //print current time in 12 hour format
        System.out.println("Current time in 12 hour format: " + new SimpleDateFormat("hh:mm a").format(new Date()));

        try {
            String[] parts = message.split(",");
            if (parts.length == 2) {
                String name = parts[0].trim();
                int age = Integer.parseInt(parts[1].trim());
                Person person = new Person();
                person.setName(name);
                person.setAge(age);
                personRepository.save(person);
                System.out.println("Person saved successfully: " + person);
            } else {
                // Log a warning or handle the message format error
                System.out.println("Invalid message format: " + message);
            }
        } catch (NumberFormatException e) {
            // Log the error and handle invalid age format
            System.err.println("Error parsing age from message: " + message);
        } catch (Exception e) {
            // Log other unexpected exceptions
            System.err.println("Unexpected error occurred: " + e.getMessage());
        }
    }
}
