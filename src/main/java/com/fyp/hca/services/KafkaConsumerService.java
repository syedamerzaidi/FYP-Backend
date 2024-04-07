package com.fyp.hca.services;

import com.fyp.hca.entity.Patient;
import com.fyp.hca.repositories.PatientRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class KafkaConsumerService {

    private final PatientRepository patientRepository;

    public KafkaConsumerService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @KafkaListener(topics = "${kafka.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(String message) {
        try {
            String[] parts = message.split(",");
            if (parts.length == 27) {
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
                Patient patient = new Patient();
                patient.setFirstName(firstName);
                patient.setLastName(lastName);
                patient.setCnic(cnic);
                patient.setAge(age);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date admissionDate = dateFormat.parse(admissionDateStr);
                patient.setAdmissionDate(new java.sql.Date(admissionDate.getTime()));
                patient.setChronicdisease(chronicDisease);
                patient.setGender(gender);
                patient.setRespiratory(respiratory);
                patient.setWeaknessPain(weaknessPain);
                patient.setFever(fever);
                patient.setGastrointestinal(gastrointestinal);
                patient.setNausea(nausea);
                patient.setCardiac(cardiac);
                patient.setHighFever(highFever);
                patient.setKidney(kidney);
                patient.setAsymptomatic(asymptomatic);
                patient.setDiabetes(diabetes);
                patient.setNeuro(neuro);
                patient.setHypertension(hypertension);
                patient.setCancer(cancer);
                patient.setThyroid(thyroid);
                patientRepository.save(patient);
                System.out.println("Patient saved successfully: " + patient);
            } else {
                System.out.println("Invalid message format: " + message);
            }
        }  catch (Exception e) {
            System.err.println("Unexpected error occurred: " + e.getMessage());
        }
    }
}
