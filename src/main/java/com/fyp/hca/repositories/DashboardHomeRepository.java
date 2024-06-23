package com.fyp.hca.repositories;

import com.fyp.hca.entity.Disease;
import com.fyp.hca.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DashboardHomeRepository extends JpaRepository<Patient, Integer> {
    @Query("SELECT COUNT(p) FROM Patient p WHERE p.disease = :disease")
    Long getTotalPopulation(Disease disease);

    @Query("SELECT COUNT(p) FROM Patient p WHERE p.disease = :disease AND p.deathBinary = false")
    Long getRecoveredPopulation(Disease disease);

    @Query("SELECT COUNT(p) FROM Patient p WHERE p.disease = :disease AND p.deathBinary = true")
    Long getDeathPopulation(Disease disease);

    @Query("SELECT COUNT(p) FROM Patient p WHERE p.disease = :disease AND p.chronicdisease = true")
    Long getChronicPopulation(Disease disease);
}
