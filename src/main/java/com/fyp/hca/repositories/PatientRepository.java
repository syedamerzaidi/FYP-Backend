package com.fyp.hca.repositories;

import com.fyp.hca.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
    long countByDiseaseId(int diseaseId);

    long countByHospitalId(Integer hospitalId);
}