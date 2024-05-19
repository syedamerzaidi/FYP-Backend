package com.fyp.hca.services;

import com.fyp.hca.entity.Tehsil;
import com.fyp.hca.repositories.HospitalRepository;
import com.fyp.hca.repositories.TehsilRepository;
import com.fyp.hca.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TehsilService {
    private final TehsilRepository tehsilRepository;
    private final HospitalRepository hospitalRepository;
    private final UsersRepository userRepository;

    @Autowired
    public TehsilService(TehsilRepository tehsilRepository, HospitalRepository hospitalRepository, UsersRepository userRepository) {
        this.tehsilRepository = tehsilRepository;
        this.hospitalRepository = hospitalRepository;
        this.userRepository = userRepository;
    }

    public void addTehsil(Tehsil tehsil) {
        tehsilRepository.save(tehsil);
    }

    public List<Tehsil> getTehsil() {
        return tehsilRepository.findAll();
    }

    public Optional<Tehsil> getTehsilById(Integer id) {
        return tehsilRepository.findById(id);
    }

    public List<Map<String, Object>> getTehsilIdAndName() {
        return tehsilRepository.findTehsilIdAndName();
    }

    public void deleteTehsil(Integer id) {
        tehsilRepository.deleteById(id);
    }

    public void updateTehsil(Tehsil tehsil) {
        tehsilRepository.save(tehsil);
    }
    public boolean isTehsilAssociated(Integer tehsilId) {
        long hospitalCount = hospitalRepository.countByTehsilId(tehsilId);
        long userCount = userRepository.countByTehsilId(tehsilId);
       return hospitalCount > 0 || userCount > 0;
    }
}