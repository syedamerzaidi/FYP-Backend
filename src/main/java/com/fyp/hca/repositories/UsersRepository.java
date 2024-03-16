package com.fyp.hca.repositories;

import com.fyp.hca.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByEmailAndPassword(String username, String password);

    @Query("SELECT u.id as id, u.firstName as firstName, u.lastName as lastName, u.usertype as usertype, u.contact as contact, u.email as email, u.password as password, " +
            "u.province.id as province_id, p.name as province_name, " +
            "u.division.id as division_id, d.name as division_name, " +
            "u.district.id as district_id, dis.name as district_name, " +
            "u.tehsil.id as tehsil_id, t.name as tehsil_name, " +
            "u.hospital.id as hospital_id, h.name as hospital_name, " +
            "u.createdOn as created_on, u.updatedOn as updated_on " +
            "FROM Users u " +
            "LEFT JOIN u.province p " +
            "LEFT JOIN u.division d " +
            "LEFT JOIN u.district dis " +
            "LEFT JOIN u.tehsil t " +
            "LEFT JOIN u.hospital h")
    List<Map<String, Object>> getAllUsers();
}
