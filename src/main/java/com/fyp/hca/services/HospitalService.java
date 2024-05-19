package com.fyp.hca.services;

import com.fyp.hca.entity.Hospital;
import com.fyp.hca.entity.Tehsil;
import com.fyp.hca.entity.Users;
import com.fyp.hca.repositories.HospitalRepository;
import com.fyp.hca.repositories.PatientRepository;
import com.fyp.hca.repositories.UsersRepository;
import org.hibernate.annotations.SecondaryRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class HospitalService {
    @Autowired
    private final HospitalRepository hospitalRepository;
    private final PatientRepository patientRepository;
    private final UsersRepository userRepository;

    @Autowired
    public HospitalService(HospitalRepository hospitalRepository, PatientRepository patientRepository, UsersRepository userRepository) {
        this.hospitalRepository = hospitalRepository;
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
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
    public List<Map<String, Object>> getHospitalIdAndName() {
        return hospitalRepository.findHospitalIdAndName();
    }
    public boolean isHospitalAssociated(Integer hospitalId) {
        long patientCount = patientRepository.countByHospitalId(hospitalId);
        long userCount = userRepository.countByHospitalId(hospitalId);
        return patientCount > 0 || userCount > 0;
    }
}