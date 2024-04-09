package com.fyp.hca.repositories;

import com.fyp.hca.entity.District;
import com.fyp.hca.entity.Province;
import com.fyp.hca.entity.Tehsil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {
    @Query("SELECT new com.fyp.hca.entity.District(t.id, t.name) FROM District t")
    List<Object[]> findDistrictIdAndName();

    long countByDivisionId(Integer divisionId);
}
