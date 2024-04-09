package com.fyp.hca.services;

import com.fyp.hca.entity.Disease;
import com.fyp.hca.entity.Users;
import com.fyp.hca.repositories.DiseaseRepository;
import com.fyp.hca.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiseaseService {

    private final DiseaseRepository diseaseRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public DiseaseService(DiseaseRepository diseaseRepository, PatientRepository patientRepository) {
        this.diseaseRepository = diseaseRepository;
        this.patientRepository = patientRepository;
    }
    public void addDisease(Disease disease) {
        diseaseRepository.save(disease);
    }

    public List<Disease> getDiseases() {
        return new ArrayList<Disease>(diseaseRepository.findAll());
    }

    public void deleteDisease(Integer id) {
        diseaseRepository.deleteById(id);
    }

    public void updateDisease(Disease disease) {
        diseaseRepository.save(disease);
    }
    public boolean isDiseaseAssociated(Integer diseaseId) {
        long patientCount = patientRepository.countByDiseaseId(diseaseId);
        return patientCount > 0;
    }

}
