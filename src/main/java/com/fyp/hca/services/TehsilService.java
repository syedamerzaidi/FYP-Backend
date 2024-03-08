package com.fyp.hca.services;

import com.fyp.hca.entity.Tehsil;
import com.fyp.hca.repositories.TehsilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TehsilService {
    @Autowired
    TehsilRepository tehsilRepository;

    public TehsilService(TehsilRepository tehsilRepository) {
        this.tehsilRepository = tehsilRepository;
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

    public List<Tehsil> getTehsilIdAndName() {
        return tehsilRepository.findTehsilIdAndName();
    }

    public void deleteTehsil(Integer id) {
        tehsilRepository.deleteById(id);
    }

    public void updateTehsil(Tehsil tehsil) {
        tehsilRepository.save(tehsil);
    }
}