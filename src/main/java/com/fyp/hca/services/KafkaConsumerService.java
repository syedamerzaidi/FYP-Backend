package com.fyp.hca.services;

import com.fyp.hca.entity.Patient;
import com.fyp.hca.repositories.DiseaseRepository;
import com.fyp.hca.repositories.HospitalRepository;
import com.fyp.hca.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
public class KafkaConsumerService {


    private final PatientRepository patientRepository;
    private final HospitalRepository hospitalRepository;
    private final DiseaseRepository diseaseRepository;

    @Autowired
    public KafkaConsumerService(PatientRepository patientRepository, HospitalRepository hospitalRepository, DiseaseRepository diseaseRepository) {
        this.patientRepository = patientRepository;
        this.hospitalRepository = hospitalRepository;
        this.diseaseRepository = diseaseRepository;
    }

    @KafkaListener(topics = "${kafka.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(String message) {
        String[] parts = message.split(",");
        if (parts.length == 22) {
            try {
                Patient patient = createPatient(parts);
                patientRepository.save(patient);
            } catch (ParseException e) {
                throw new RuntimeException("Failed to parse admission date: " + e.getMessage());
            } catch (RuntimeException e) {
                throw new RuntimeException("Failed to save patient data: " + e.getMessage());
            }
        }
    }

    private Patient createPatient(String[] parts) throws ParseException {
        int hospitalId = Integer.parseInt(parts[21].trim());
        int diseaseId = Integer.parseInt(parts[22].trim());

        Patient patient = new Patient();
        patient.setFirstName(parts[0].trim());
        patient.setLastName(parts[1].trim());
        patient.setCnic(parts[2].trim());
        patient.setAge(Integer.parseInt(parts[3].trim()));
        patient.setAdmissionDate(new Date(new SimpleDateFormat("yyyy-MM-dd").parse(parts[4].trim()).getTime()));
        patient.setChronicdisease(parts[5].trim());
        patient.setGender(parts[6].trim());
        patient.setRespiratory(parseBoolean(parts[7]));
        patient.setWeaknessPain(parseBoolean(parts[8]));
        patient.setFever(parseBoolean(parts[9]));
        patient.setGastrointestinal(parseBoolean(parts[10]));
        patient.setNausea(parseBoolean(parts[11]));
        patient.setCardiac(parseBoolean(parts[12]));
        patient.setHighFever(parseBoolean(parts[13]));
        patient.setKidney(parseBoolean(parts[14]));
        patient.setAsymptomatic(parseBoolean(parts[15]));
        patient.setDiabetes(parseBoolean(parts[16]));
        patient.setNeuro(parseBoolean(parts[17]));
        patient.setHypertension(parseBoolean(parts[18]));
        patient.setCancer(parseBoolean(parts[19]));
        patient.setThyroid(parseBoolean(parts[20]));
        patient.setHospital(hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new RuntimeException("Hospital not found")));
        patient.setDisease(diseaseRepository.findById(diseaseId)
                .orElseThrow(() -> new RuntimeException("Disease not found")));

        return patient;
    }

    private boolean parseBoolean(String value) {
        return Boolean.parseBoolean(value.trim());
    }
}