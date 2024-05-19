package com.fyp.hca.repositories;

import com.fyp.hca.entity.Hospital;
import com.fyp.hca.entity.Tehsil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Integer> {
    @Query("SELECT h.id as id, h.name as name FROM Hospital h")
    List<Map<String, Object>> findHospitalIdAndName();

    long countByTehsilId(Integer tehsilId);
}