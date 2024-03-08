package com.fyp.hca.services;

import com.fyp.hca.entity.Division;
import com.fyp.hca.repositories.DivisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DivisionService {
    @Autowired
    DivisionRepository divisionRepository;

    public void addDivision(Division division) {
        divisionRepository.save(division);
    }

    public List<Division> getDivision() {
        return divisionRepository.findAll();
    }

    public Optional<Division> getDivisionById(Integer id) {
        return divisionRepository.findById(id);
    }

    public List<Division> getDivisionIdAndName() {
        return divisionRepository.findDivisionIdAndName();
    }

    public void deleteDivision(Integer id) {
        divisionRepository.deleteById(id);
    }

    public void updateDivision(Division division) {
        divisionRepository.save(division);
    }
}
