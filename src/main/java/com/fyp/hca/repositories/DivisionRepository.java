package com.fyp.hca.repositories;

import com.fyp.hca.entity.District;
import com.fyp.hca.entity.Division;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface DivisionRepository extends JpaRepository<Division, Integer> {

    @Query("SELECT t.id as id, t.name as name FROM Division t")
    List<Map<String, Object>> findDivisionIdAndName();

    long countByProvinceId(Integer provinceId);
}
