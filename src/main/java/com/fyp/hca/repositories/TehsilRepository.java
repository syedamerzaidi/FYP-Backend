package com.fyp.hca.repositories;

import com.fyp.hca.entity.Tehsil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TehsilRepository extends JpaRepository<Tehsil, Integer> {
    @Query("SELECT new com.fyp.hca.entity.Tehsil(t.id, t.name) FROM Tehsil t")
    List<Object[]> findTehsilIdAndName();

    long countByDistrictId(Integer districtId);
}