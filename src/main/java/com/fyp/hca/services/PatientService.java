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
import java.util.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fyp.hca.model.PaginatedResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;

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
    public List<Patient> getPatientsByHospitalId(Integer hospitalId) {
        return new ArrayList<Patient>(patientRepository.findByHospitalId(hospitalId));
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

    public PaginatedResponse<Patient> getTableData(int start, int size, String filters, String sorting, String globalFilter) {
        Pageable pageable = PageRequest.of(start / size, size, parseSort(sorting));
        Specification<Patient> specification = parseFilters(filters, globalFilter);

        List<Patient> patients = patientRepository.findAll(specification, pageable).getContent();
        long totalCount = patientRepository.count();

        return new PaginatedResponse<>(patients, totalCount);
    }

    private Sort parseSort(String sorting) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<Map<String, Object>> sortingList = mapper.readValue(sorting, List.class);
            if (sortingList.isEmpty()) {
                return Sort.unsorted();
            }
            Map<String, Object> sortObj = sortingList.get(0);
            String property = (String) sortObj.get("id");
            boolean desc = (Boolean) sortObj.get("desc");
            return Sort.by(desc ? Sort.Direction.DESC : Sort.Direction.ASC, property);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return Sort.unsorted();
        }
    }

    private Specification<Patient> parseFilters(String filters, String globalFilter) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<Map<String, Object>> filtersList = mapper.readValue(filters, List.class);
            return (root, query, criteriaBuilder) -> {
                List<Predicate> predicates = new ArrayList<>();

                for (Map<String, Object> filter : filtersList) {
                    String field = (String) filter.get("id");
                    String value = (String) filter.get("value");

                    if (isNumeric(value)) {
                        predicates.add(criteriaBuilder.equal(root.get(field), Integer.parseInt(value)));
                    } else if (isBoolean(value)) {
                        predicates.add(criteriaBuilder.equal(root.get(field), parseBoolean(value)));
                    } else if (isDate(value)) {
                        try {
                            LocalDate date = Instant.parse(value).atZone(ZoneId.systemDefault()).toLocalDate();
                            predicates.add(criteriaBuilder.equal(criteriaBuilder.function("DATE", LocalDate.class, root.get(field)), date));
                        } catch (DateTimeParseException e) {
                            e.printStackTrace();
                        }
                    } else {
                        String strValue = value.toUpperCase();
                        if (isStringField(field)) {
                            predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get(field)), "%" + strValue + "%"));
                        } else {
                            predicates.add(criteriaBuilder.equal(root.get(field), value));
                        }
                    }
                }

                if (globalFilter != null && !globalFilter.isEmpty()) {
                    String globalFilterUpper = globalFilter.toUpperCase();
                    List<Predicate> globalPredicates = new ArrayList<>();

                    // Global filter for all boolean fields
                    if (isBoolean(globalFilter)) {
                        globalPredicates.add(criteriaBuilder.equal(root.get("blood"), parseBoolean(globalFilter)));
                        globalPredicates.add(criteriaBuilder.equal(root.get("chronicdisease"), parseBoolean(globalFilter)));
                        globalPredicates.add(criteriaBuilder.equal(root.get("diabetes"), parseBoolean(globalFilter)));
                        globalPredicates.add(criteriaBuilder.equal(root.get("highFever"), parseBoolean(globalFilter)));
                        globalPredicates.add(criteriaBuilder.equal(root.get("fever"), parseBoolean(globalFilter)));
                        globalPredicates.add(criteriaBuilder.equal(root.get("hypertension"), parseBoolean(globalFilter)));
                        globalPredicates.add(criteriaBuilder.equal(root.get("cardiac"), parseBoolean(globalFilter)));
                        globalPredicates.add(criteriaBuilder.equal(root.get("weaknessPain"), parseBoolean(globalFilter)));
                        globalPredicates.add(criteriaBuilder.equal(root.get("respiratory"), parseBoolean(globalFilter)));
                        globalPredicates.add(criteriaBuilder.equal(root.get("cancer"), parseBoolean(globalFilter)));
                        globalPredicates.add(criteriaBuilder.equal(root.get("thyroid"), parseBoolean(globalFilter)));
                        globalPredicates.add(criteriaBuilder.equal(root.get("prostate"), parseBoolean(globalFilter)));
                        globalPredicates.add(criteriaBuilder.equal(root.get("kidney"), parseBoolean(globalFilter)));
                        globalPredicates.add(criteriaBuilder.equal(root.get("neuro"), parseBoolean(globalFilter)));
                        globalPredicates.add(criteriaBuilder.equal(root.get("nausea"), parseBoolean(globalFilter)));
                        globalPredicates.add(criteriaBuilder.equal(root.get("asymptomatic"), parseBoolean(globalFilter)));
                        globalPredicates.add(criteriaBuilder.equal(root.get("gastrointestinal"), parseBoolean(globalFilter)));
                        globalPredicates.add(criteriaBuilder.equal(root.get("ortho"), parseBoolean(globalFilter)));
                        globalPredicates.add(criteriaBuilder.equal(root.get("respiratoryCD"), parseBoolean(globalFilter)));
                        globalPredicates.add(criteriaBuilder.equal(root.get("cardiacsCD"), parseBoolean(globalFilter)));
                        globalPredicates.add(criteriaBuilder.equal(root.get("kidneyCD"), parseBoolean(globalFilter)));
                        globalPredicates.add(criteriaBuilder.equal(root.get("deathBinary"), parseBoolean(globalFilter)));
                    }

                    // Global filter for string fields
                    if (isStringField("firstName")) {
                        globalPredicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("firstName")), "%" + globalFilterUpper + "%"));
                    }
                    if (isStringField("lastName")) {
                        globalPredicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("lastName")), "%" + globalFilterUpper + "%"));
                    }
                    if (isStringField("cnic")) {
                        globalPredicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("cnic")), "%" + globalFilterUpper + "%"));
                    }
                    if (isStringField("gender")) {
                        globalPredicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("gender")), "%" + globalFilterUpper + "%"));
                    }

                    // Global filter for numeric fields
                    if (isNumeric(globalFilter)) {
                        globalPredicates.add(criteriaBuilder.equal(root.get("id"), Integer.parseInt(globalFilter)));
                        globalPredicates.add(criteriaBuilder.equal(root.get("age"), Integer.parseInt(globalFilter)));
                    }

                    // Global filter for date fields
                    if (isDate(globalFilter)) {
                        try {
                            LocalDate date = Instant.parse(globalFilter).atZone(ZoneId.systemDefault()).toLocalDate();
                            globalPredicates.add(criteriaBuilder.equal(criteriaBuilder.function("DATE", LocalDate.class, root.get("admissionDate")), date));
                        } catch (DateTimeParseException e) {
                            e.printStackTrace();
                        }
                    }

                    predicates.add(criteriaBuilder.or(globalPredicates.toArray(new Predicate[0])));
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            };
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean isStringField(String field) {
        // Add more string fields as necessary
        return Arrays.asList("firstName", "lastName", "cnic", "gender").contains(field);
    }

    private boolean isNumeric(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isBoolean(String value) {
        return "Yes".equalsIgnoreCase(value) || "No".equalsIgnoreCase(value) || "1".equals(value) || "0".equals(value);
    }

    private Boolean parseBoolean(String value) {
        return "Yes".equalsIgnoreCase(value) || "No".equalsIgnoreCase(value) || "1".equals(value) || "0".equals(value);
    }

    private boolean isDate(String value) {
        try {
            Instant.parse(value);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}