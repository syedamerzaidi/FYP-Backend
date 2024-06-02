package com.fyp.hca.repositories;

import com.fyp.hca.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
    long countByDiseaseId(int diseaseId);

    long countByHospitalId(Integer hospitalId);

    ArrayList<Patient> findByHospitalId(Integer hospitalId);
}