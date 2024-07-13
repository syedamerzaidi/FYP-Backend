package com.fyp.hca.repositories;

import com.fyp.hca.entity.District;
import com.fyp.hca.entity.Hospital;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Integer>, JpaSpecificationExecutor<Hospital> {
    @Query("SELECT h.id as id, h.name as name FROM Hospital h")
    List<Map<String, Object>> findHospitalIdAndName();

    long countByTehsilId(Integer tehsilId);

    @Query("SELECT h.id as id, h.name as name FROM Hospital h WHERE h.tehsil.id IN :tehsilIds")
    List<Map<String, Object>> findHospitalIdAndNameByTehsilIds(List<Integer> tehsilIds);

    @Query("SELECT h  FROM Hospital h WHERE h.tehsil.district.division.province.id = :provinceId")
    List<Hospital> findAllByProvinceId(Integer provinceId, Sort id);

    @Query("SELECT h  FROM Hospital h WHERE h.tehsil.district.division.id = :divisionId")
    List<Hospital> findAllByDivisionId(Integer divisionId, Sort id);

    @Query("SELECT h  FROM Hospital h WHERE h.tehsil.id = :tehsilId")
    List<Hospital> findAllByTehsilId(Integer tehsilId, Sort id);

    @Query("SELECT h.id as id, h.name as name FROM Hospital h")
    List<Map<String, Object>> findHospitalIdAndNameSuper();

    @Query("SELECT h.id as id, h.name as name FROM Hospital h WHERE h.tehsil.district.division.id = :divisionId")
    List<Map<String, Object>> findHospitalIdAndNameDivision(Integer divisionId);

    @Query("SELECT h.id as id, h.name as name FROM Hospital h WHERE h.tehsil.id = :tehsilId")
    List<Map<String, Object>> findHospitalIdAndNameTehsil(Integer tehsilId);

    @Query("SELECT h.id as id, h.name as name FROM Hospital h WHERE h.tehsil.district.id = :districtId")
    List<Map<String, Object>> findHospitalIdAndNameDistrict(Integer districtId);

    @Query("SELECT h.id as id, h.name as name FROM Hospital h WHERE h.tehsil.district.division.province.id = :provinceId")
    List<Map<String, Object>> findHospitalIdAndNameProvince(Integer provinceId);
}