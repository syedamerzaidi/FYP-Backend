package com.fyp.hca.services;

import com.fyp.hca.entity.Hospital;
import com.fyp.hca.entity.Tehsil;
import com.fyp.hca.entity.Users;
import com.fyp.hca.repositories.HospitalRepository;
import org.hibernate.annotations.SecondaryRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HospitalService {
    @Autowired
    HospitalRepository hospitalRepository;

    public HospitalService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    public void addHospital(Hospital hospital) {
        hospitalRepository.save(hospital);
    }

    public List<Hospital> getHospitals() {
        return new ArrayList<Hospital>(hospitalRepository.findAll());
    }

    public Optional<Hospital> getHospitalById(Integer id) {
        return hospitalRepository.findById(id);
    }

    public void deleteHospital(Integer id) {
        hospitalRepository.deleteById(id);
    }

    public void updateHospital(Hospital hospital) {
        hospitalRepository.save(hospital);
    }
    public List<Hospital> getHospitalIdAndName() {
        return hospitalRepository.findHospitalIdAndName();
    }

}