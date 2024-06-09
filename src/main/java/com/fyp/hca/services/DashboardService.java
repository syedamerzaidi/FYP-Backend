package com.fyp.hca.services;

import com.fyp.hca.entity.Disease;
import com.fyp.hca.entity.Patient;
import com.fyp.hca.entity.Users;
import com.fyp.hca.model.AnalyticResultModel;
import com.fyp.hca.model.FiltersRequestModel;
import com.fyp.hca.model.StatisticResponseModel;
import com.fyp.hca.repositories.DiseaseRepository;
import com.fyp.hca.repositories.PatientRepository;
import com.fyp.hca.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DashboardService {

    private final PatientRepository patientRepository;
    private final UsersRepository usersRepository;
    private final DiseaseRepository diseaseRepository;

    @Autowired
    public DashboardService(PatientRepository patientRepository, UsersRepository usersRepository, DiseaseRepository diseaseRepository) {
        this.patientRepository = patientRepository;
        this.usersRepository = usersRepository;
        this.diseaseRepository = diseaseRepository;
    }

    public AnalyticResultModel getDashboardData(Integer userId, Integer diseaseId, FiltersRequestModel filters) {
        AnalyticResultModel result = new AnalyticResultModel();
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        int totalPatients = fetchTotalPatientsCountByUserType(user, diseaseId);
        List<Patient> filteredpatients;
        if (filters == null) {
            filteredpatients = fetchPatientsByUserType(user, diseaseId);
        } else {
            filteredpatients = applyFilters(fetchPatientsByUserType(user, diseaseId), filters, diseaseId);
        }
        result.setStatisticResponse(calculateStatistics(filteredpatients, totalPatients));
        return result;
    }

    private int fetchTotalPatientsCountByUserType(Users user, Integer diseaseId) {
        String userType = user.getUsertype();
        Optional<Disease> diseaseOpt = diseaseRepository.findById(diseaseId);
        if (diseaseOpt.isEmpty()) {
            return 0;
        }
        Disease disease = diseaseOpt.get();

        return switch (userType) {
            case "Super Administrator" -> patientRepository.findAll(Sort.by(Sort.Direction.ASC, "id")).stream()
                    .filter(patient -> patient.getDisease() != null && patient.getDisease().equals(disease))
                    .toList().size();
            case "Hospital Administrator" -> patientRepository.findByHospitalId(user.getHospital().getId(), Sort.by(Sort.Direction.ASC, "id")).stream()
                    .filter(patient -> patient.getDisease() != null && patient.getDisease().equals(disease))
                    .toList().size();
            case "Tehsil Administrator" -> patientRepository.findByTehsilId(user.getTehsil().getId(), Sort.by(Sort.Direction.ASC, "id")).stream()
                    .filter(patient -> patient.getDisease() != null && patient.getDisease().equals(disease))
                    .toList().size();
            case "District Administrator" -> patientRepository.findByDistrictId(user.getDistrict().getId(), Sort.by(Sort.Direction.ASC, "id")).stream()
                    .filter(patient -> patient.getDisease() != null && patient.getDisease().equals(disease))
                    .toList().size();
            case "Division Administrator" -> patientRepository.findByDivisionId(user.getDivision().getId(), Sort.by(Sort.Direction.ASC, "id")).stream()
                    .filter(patient -> patient.getDisease() != null && patient.getDisease().equals(disease))
                    .toList().size();
            case "Province Administrator" -> patientRepository.findByProvinceId(user.getProvince().getId(), Sort.by(Sort.Direction.ASC, "id")).stream()
                    .filter(patient -> patient.getDisease() != null && patient.getDisease().equals(disease))
                    .toList().size();
            default -> throw new IllegalArgumentException("Unsupported user type: " + userType);
        };
    }

    private List<Patient> fetchPatientsByUserType(Users user, Integer diseaseId) {
        String userType = user.getUsertype();
        Optional<Disease> diseaseOpt = diseaseRepository.findById(diseaseId);
        if (diseaseOpt.isEmpty()) {
            return Collections.emptyList();
        }
        Disease disease = diseaseOpt.get();

        return switch (userType) {
            case "Super Administrator" -> patientRepository.findAll(Sort.by(Sort.Direction.ASC, "id")).stream()
                    .filter(patient -> patient.getDisease() != null && patient.getDisease().equals(disease))
                    .collect(Collectors.toList());
            case "Hospital Administrator" -> patientRepository.findByHospitalId(user.getHospital().getId(), Sort.by(Sort.Direction.ASC, "id")).stream()
                    .filter(patient -> patient.getDisease() != null && patient.getDisease().equals(disease))
                    .collect(Collectors.toList());
            case "Tehsil Administrator" -> patientRepository.findByTehsilId(user.getTehsil().getId(), Sort.by(Sort.Direction.ASC, "id")).stream()
                    .filter(patient -> patient.getDisease() != null && patient.getDisease().equals(disease))
                    .collect(Collectors.toList());
            case "District Administrator" -> patientRepository.findByDistrictId(user.getDistrict().getId(), Sort.by(Sort.Direction.ASC, "id")).stream()
                    .filter(patient -> patient.getDisease() != null && patient.getDisease().equals(disease))
                    .collect(Collectors.toList());
            case "Division Administrator" -> patientRepository.findByDivisionId(user.getDivision().getId(), Sort.by(Sort.Direction.ASC, "id")).stream()
                    .filter(patient -> patient.getDisease() != null && patient.getDisease().equals(disease))
                    .collect(Collectors.toList());
            case "Province Administrator" -> patientRepository.findByProvinceId(user.getProvince().getId(), Sort.by(Sort.Direction.ASC, "id")).stream()
                    .filter(patient -> patient.getDisease() != null && patient.getDisease().equals(disease))
                    .collect(Collectors.toList());
            default -> throw new IllegalArgumentException("Unsupported user type: " + userType);
        };
    }



    private List<Patient> applyFilters(List<Patient> patients, FiltersRequestModel filters, Integer diseaseId) {
        return patients.stream()
                .filter(patient -> filters.getHospitalIds() == null || filters.getHospitalIds().isEmpty() || filters.getHospitalIds().contains(patient.getHospital().getId()))
                .filter(patient -> filters.getSymptoms() == null || filters.getSymptoms().isEmpty() || filters.getSymptoms().stream().allMatch(symptom -> hasSymptom(patient, symptom)))
                .filter(patient -> filters.getAdmissionStartDate() == null || !patient.getAdmissionDate().before(filters.getAdmissionStartDate()))
                .filter(patient -> filters.getAdmissionEndDate() == null || !patient.getAdmissionDate().after(filters.getAdmissionEndDate()))
                .filter(patient -> filters.getGender() == null || (filters.getGender()==2) || filters.getGender().toString().equals(patient.getGender()))
                .filter(patient -> filters.getAgeStart() == null || patient.getAge() >= filters.getAgeStart())
                .filter(patient -> filters.getAgeEnd() == null || patient.getAge() <= filters.getAgeEnd())
                .filter(patient -> diseaseId == null || (patient.getDisease() != null && diseaseId.equals(patient.getDisease().getId())))
                .collect(Collectors.toList());
    }

    private boolean hasSymptom(Patient patient, String symptom) {
        return switch (symptom.toLowerCase()) {
            case "blood" -> patient.getBlood();
            case "chronicdisease" -> patient.getChronicdisease();
            case "diabetes" -> patient.getDiabetes();
            case "highfever" -> patient.getHighFever();
            case "fever" -> patient.getFever();
            case "hypertension" -> patient.getHypertension();
            case "cardiac" -> patient.getCardiac();
            case "weaknesspain" -> patient.getWeaknessPain();
            case "respiratory" -> patient.getRespiratory();
            case "cancer" -> patient.getCancer();
            case "thyroid" -> patient.getThyroid();
            case "prostate" -> patient.getProstate();
            case "kidney" -> patient.getKidney();
            case "neuro" -> patient.getNeuro();
            case "nausea" -> patient.getNausea();
            case "asymptomatic" -> patient.getAsymptomatic();
            case "gastrointestinal" -> patient.getGastrointestinal();
            case "ortho" -> patient.getOrtho();
            case "respiratorycd" -> patient.getRespiratoryCD();
            case "cardiacscd" -> patient.getCardiacsCD();
            default -> false;
        };
    }

    private StatisticResponseModel calculateStatistics(List<Patient> filteredPatients,int totalPatients) {
        StatisticResponseModel statistics = new StatisticResponseModel();
        statistics.setPatientsTotalCount(totalPatients);
        statistics.setPatientsCount(filteredPatients.size());

        long deathsCount = filteredPatients.stream().filter(Patient::getDeathBinary).count();
        statistics.setPatientsDeathsCount(deathsCount);

        long recoveredCount = filteredPatients.stream().filter(patient -> !patient.getDeathBinary()).count();
        statistics.setPatientsRecoveredCount(recoveredCount);

        long chronicCount = filteredPatients.stream().filter(Patient::getChronicdisease).count();
        statistics.setPatientsChronicCount(chronicCount);

        Date admissionStartDate = filteredPatients.stream()
                .map(Patient::getAdmissionDate)
                .min(Comparator.naturalOrder())
                .orElse(null);

        Date admissionEndDate = filteredPatients.stream()
                .map(Patient::getAdmissionDate)
                .max(Comparator.naturalOrder())
                .orElse(null);

        statistics.setAdmissionStartDate(admissionStartDate);
        statistics.setAdmissionEndDate(admissionEndDate);

        return statistics;
    }

}
