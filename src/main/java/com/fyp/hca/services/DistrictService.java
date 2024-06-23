package com.fyp.hca.services;

import com.fyp.hca.entity.District;
import com.fyp.hca.repositories.DistrictRepository;
import com.fyp.hca.repositories.TehsilRepository;
import com.fyp.hca.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DistrictService {
    private final DistrictRepository districtRepository;
    private final TehsilRepository tehsilRepository;
    private final UsersRepository userRepository;

    @Autowired
    public DistrictService(DistrictRepository districtRepository, TehsilRepository tehsilRepository, UsersRepository userRepository) {
        this.districtRepository = districtRepository;
        this.tehsilRepository = tehsilRepository;
        this.userRepository = userRepository;
    }
    public List<District> getDistrict() {
        return districtRepository.findAll();
    }

    public Optional<District> getDistrictById(Integer id) {
        return districtRepository.findById(id);
    }

    public List<Map<String, Object>> getDistrictIdAndName() {
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

    public boolean isDistrictAssociated(Integer districtId) {
        long tehsilCount = tehsilRepository.countByDistrictId(districtId);
        long userCount = userRepository.countByTehsilId(districtId);
        return tehsilCount > 0 || userCount > 0;
    }

    public List<Map<String, Object>> getDistrictIdAndNameByDivisionIds(List<Integer> divisionIds) {
        return districtRepository.findDistrictIdAndNameByDivisionIds(divisionIds);
    }

}
