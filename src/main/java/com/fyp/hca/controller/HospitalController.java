package com.fyp.hca.controller;

import com.fyp.hca.entity.Division;
import com.fyp.hca.entity.Hospital;
import com.fyp.hca.services.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    @PostMapping(value = "hospital/add")
    public void addHospital(@RequestBody Hospital hospital){
        hospitalService.addHospital(hospital);
    }

    @GetMapping(value = "hospital/get")
    public List<Hospital> getHospitals(){
        return hospitalService.getHospitals();
    }

    @GetMapping(value="hospital/getid/{id}")
    public Optional<Hospital> getHospitalById(@PathVariable Integer id){
        return hospitalService.getHospitalById(id);
    }

    @DeleteMapping(value = "/hospital/delete/{id}")
    public void deleteHospital(@PathVariable Integer id){
        hospitalService.deleteHospital(id);
    }

    @PutMapping(value = "/hospital/update")
    public void updateHospital(@RequestBody Hospital hospital){
        hospitalService.updateHospital(hospital);
    }
}