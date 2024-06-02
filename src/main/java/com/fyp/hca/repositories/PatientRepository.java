package com.fyp.hca.repositories;

import com.fyp.hca.entity.*;
import com.fyp.hca.model.StatisticResponseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
    long countByDiseaseId(int diseaseId);

    long countByHospitalId(Integer hospitalId);

    ArrayList<Patient> findByHospitalId(Integer hospitalId);
}