package com.fyp.hca.repositories;

import com.fyp.hca.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer>, JpaSpecificationExecutor<Users> {

    @Query("SELECT u FROM Users u WHERE u.email = :email AND u.password = :password")
    Optional<Users> findByEmailAndPassword(@Param("email") String email, @Param("password") String password);

    long countByTehsilId(Integer tehsilId);

    long countByHospitalId(Integer hospitalId);

    Optional<Users> findByEmail(String email);
}
