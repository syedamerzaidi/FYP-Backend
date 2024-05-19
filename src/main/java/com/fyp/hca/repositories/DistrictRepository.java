package com.fyp.hca.repositories;

import com.fyp.hca.entity.District;
import com.fyp.hca.entity.Province;
import com.fyp.hca.entity.Tehsil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {
    @Query("SELECT t.id as id, t.name as name FROM District t")
    List<Map<String, Object>> findDistrictIdAndName();

    long countByDivisionId(Integer divisionId);
}
