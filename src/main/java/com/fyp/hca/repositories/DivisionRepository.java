package com.fyp.hca.repositories;

import com.fyp.hca.entity.District;
import com.fyp.hca.entity.Division;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DivisionRepository extends JpaRepository<Division, Integer> {

    @Query("SELECT new com.fyp.hca.entity.Division(t.id, t.name) FROM Division t")
    List<Division> findDivisionIdAndName();

    long countByProvinceId(Integer provinceId);
}
