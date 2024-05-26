package com.fyp.hca.services;

import com.fyp.hca.entity.Division;
import com.fyp.hca.repositories.DistrictRepository;
import com.fyp.hca.repositories.DivisionRepository;
import com.fyp.hca.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DivisionService {
    private final DivisionRepository divisionRepository;
    private final DistrictRepository districtRepository;
    private final UsersRepository userRepository;

    @Autowired
    public DivisionService(DivisionRepository divisionRepository, DistrictRepository districtRepository, UsersRepository userRepository) {
        this.divisionRepository = divisionRepository;
        this.districtRepository = districtRepository;
        this.userRepository = userRepository;
    }
    public void addDivision(Division division) {
        divisionRepository.save(division);
    }

    public List<Division> getDivision() {
        return divisionRepository.findAll();
    }

    public Optional<Division> getDivisionById(Integer id) {
        return divisionRepository.findById(id);
    }

    public List<Map<String, Object>> getDivisionIdAndName() {
        return divisionRepository.findDivisionIdAndName();
    }

    public void deleteDivision(Integer id) {
        divisionRepository.deleteById(id);
    }

    public void updateDivision(Division division) {
        divisionRepository.save(division);
    }

    public boolean isDivisionAssociated(Integer divisionId) {
        long districtCount = districtRepository.countByDivisionId(divisionId);
        long userCount = userRepository.countByTehsilId(divisionId);
        return districtCount > 0 || userCount > 0;
    }
    public List<Map<String, Object>> getDivisionByProvinceIds(List<Integer> provinceIds) {
        return divisionRepository.findDivisionIdAndNameByProvinceIds(provinceIds);
    }

}
