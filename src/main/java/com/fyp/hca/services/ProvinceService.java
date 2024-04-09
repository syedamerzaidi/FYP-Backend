package com.fyp.hca.services;

import com.fyp.hca.entity.Province;
import com.fyp.hca.repositories.DivisionRepository;
import com.fyp.hca.repositories.ProvinceRepository;
import com.fyp.hca.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProvinceService {
    private final ProvinceRepository provinceRepository;
    private final UsersRepository userRepository;
    private final DivisionRepository divisionRepository;
    @Autowired
    public ProvinceService(ProvinceRepository provinceRepository, UsersRepository userRepository, DivisionRepository divisionRepository) {
        this.provinceRepository = provinceRepository;
        this.userRepository = userRepository;
        this.divisionRepository = divisionRepository;
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
    public List<Province> getProvinceIdAndName() {
        return provinceRepository.findProvinceIdAndName();
    }

    public boolean isProvinceAssociated(Integer provinceId) {
        long userCount = userRepository.countByProvinceId(provinceId);
        long divisionCount = divisionRepository.countByProvinceId(provinceId);
        return userCount > 0 || divisionCount > 0;
    }
}
