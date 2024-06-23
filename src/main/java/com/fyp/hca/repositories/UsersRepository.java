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

    long countByTehsilId(Integer tehsilId);

    long countByProvinceId(Integer provinceId);

    long countByHospitalId(Integer hospitalId);
    @Query("SELECT COUNT(u) = 1 FROM Users u WHERE u.id = :userId AND u.usertype = :usertype")
    boolean existsByIdAndUsertype(Integer userId, String usertype);

}
