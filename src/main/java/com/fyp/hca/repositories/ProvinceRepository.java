package com.fyp.hca.repositories;

import com.fyp.hca.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Integer> {
    @Query("SELECT new com.fyp.hca.entity.Province(p.id, p.name) FROM Province p")
    List<Province> findProvinceIdAndName();
}
