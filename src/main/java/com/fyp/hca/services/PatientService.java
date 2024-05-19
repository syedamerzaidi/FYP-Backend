package com.fyp.hca.services;

import com.fyp.hca.entity.Disease;
import com.fyp.hca.entity.Hospital;
import com.fyp.hca.entity.Patient;
import com.fyp.hca.repositories.PatientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public void addPatient(Patient patient) {
        patientRepository.save(patient);
    }

    public List<Patient> getPatients() {
        return new ArrayList<Patient>(patientRepository.findAll());
    }

    public void deletePatient(Integer id) {
        patientRepository.deleteById(id);
    }

    public void updatePatient(Patient patient) {
        patientRepository.save(patient);
    }

    public Optional<Patient> getPatientById(Integer id) {
        return patientRepository.findById(id);
    }

    @Transactional
    public void savePatientsFromCSV(MultipartFile file,Integer hospitalId,Integer diseaseId) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            boolean isFirstLine = true;
            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                String[] data = line.split(",");
                Patient patient = new Patient();

                patient.setFirstName(data[0]);
                patient.setLastName(data[1]);
                patient.setCnic(data[2]);
                patient.setAge(Integer.parseInt(data[3]));
                try {
                    patient.setAdmissionDate(new Date(new SimpleDateFormat("MM/dd/yyyy").parse(data[4]).getTime()));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                patient.setChronicdisease(Boolean.parseBoolean(data[5]));
                patient.setGender(data[6]);
                patient.setDeathBinary(Boolean.parseBoolean(data[7]));
                patient.setRespiratory(Boolean.parseBoolean(data[8]));
                patient.setWeaknessPain(Boolean.parseBoolean(data[9]));
                patient.setFever(Boolean.parseBoolean(data[10]));
                patient.setGastrointestinal(Boolean.parseBoolean(data[11]));
                patient.setNausea(Boolean.parseBoolean(data[12]));
                patient.setCardiac(Boolean.parseBoolean(data[13]));
                patient.setHighFever(Boolean.parseBoolean(data[14]));
                patient.setKidney(Boolean.parseBoolean(data[15]));
                patient.setAsymptomatic(Boolean.parseBoolean(data[16]));
                patient.setDiabetes(Boolean.parseBoolean(data[17]));
                patient.setNeuro(Boolean.parseBoolean(data[18]));
                patient.setHypertension(Boolean.parseBoolean(data[19]));
                patient.setCancer(Boolean.parseBoolean(data[20]));
                patient.setOrtho(Boolean.parseBoolean(data[21]));
                patient.setRespiratoryCD(Boolean.parseBoolean(data[22]));
                patient.setCardiacsCD(Boolean.parseBoolean(data[23]));
                patient.setKidneyCD(Boolean.parseBoolean(data[24]));
                patient.setBlood(Boolean.parseBoolean(data[25]));
                patient.setProstate(Boolean.parseBoolean(data[26]));
                patient.setThyroid(Boolean.parseBoolean(data[27]));

                Hospital hospital = new Hospital();
                hospital.setId(hospitalId);
                patient.setHospital(hospital);

                Disease disease = new Disease();
                disease.setId(diseaseId);
                patient.setDisease(disease);

                patientRepository.save(patient);
            }
        }
    }

}
