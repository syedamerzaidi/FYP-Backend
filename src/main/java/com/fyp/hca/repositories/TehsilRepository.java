package com.fyp.hca.repositories;

import com.fyp.hca.entity.Tehsil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TehsilRepository extends JpaRepository<Tehsil, Integer> {
    @Query("SELECT t.id as id, t.name as name FROM Tehsil t")
    List<Map<String, Object>> findTehsilIdAndName();

    long countByDistrictId(Integer districtId);
}