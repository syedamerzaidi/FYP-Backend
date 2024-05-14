package com.fyp.hca.services;

import com.fyp.hca.entity.Province;
import com.fyp.hca.entity.Users;
import com.fyp.hca.repositories.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProvinceService {
    @Autowired
    ProvinceRepository provinceRepository;

    public ProvinceService(ProvinceRepository provinceRepository) {
        this.provinceRepository = provinceRepository;
    }

    public void addProvince(Province province) {
        provinceRepository.save(province);
    }

    public List<Province> getProvince() {
        return provinceRepository.findAll();
    }

    public Optional<Province> getProvinceById(Integer id) {
        return provinceRepository.findById(id);
    }

    public void deleteProvince(Integer id) {
        provinceRepository.deleteById(id);
    }

    public void updateProvince(Province province) {
        provinceRepository.save(province);
    }
}
