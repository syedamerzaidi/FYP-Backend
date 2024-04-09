package com.fyp.hca.repositories;

import com.fyp.hca.entity.Hospital;
import com.fyp.hca.entity.Tehsil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Integer> {
    @Query("SELECT new com.fyp.hca.entity.Hospital(h.id, h.name) FROM Hospital h")
    List<Hospital> findHospitalIdAndName();

    long countByTehsilId(Integer tehsilId);
}