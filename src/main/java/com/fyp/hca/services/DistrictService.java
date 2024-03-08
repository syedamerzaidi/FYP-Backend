package com.fyp.hca.services;

import com.fyp.hca.entity.District;
import com.fyp.hca.repositories.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DistrictService {
    @Autowired
    DistrictRepository districtRepository;

    public List<District> getDistrict() {
        return districtRepository.findAll();
    }

    public Optional<District> getDistrictById(Integer id) {
        return districtRepository.findById(id);
    }

    public List<District> getDistrictIdAndName() {
        return districtRepository.findDistrictIdAndName();
    }

    public void addDistrict(District district) {
        districtRepository.save(district);
    }

    public void deleteDistrict(Integer id) {
        districtRepository.deleteById(id);
    }

    public void updateDistrict(District district) {
        districtRepository.save(district);
    }
}
