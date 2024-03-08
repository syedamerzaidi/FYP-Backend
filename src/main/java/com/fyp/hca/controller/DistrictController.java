package com.fyp.hca.controller;

import com.fyp.hca.entity.District;
import com.fyp.hca.services.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DistrictController {
    @Autowired
    DistrictService districtService;


    @PostMapping(value = "/district/add")
    public void addDistrict(@RequestBody District district){
        districtService.addDistrict(district);
    }

    @GetMapping(value = "/district/get")
    public List<District> getDistrict(){
        return districtService.getDistrict();
    }

    @GetMapping(value = "/district/get/{id}")
    public Optional<District> getDistrictById(@PathVariable Integer id){
        return districtService.getDistrictById(id);
    }

    @GetMapping(value = "/district/getIdAndName")
    public List<District> getDistrictIdAndName(){
        return districtService.getDistrictIdAndName();
    }

    @DeleteMapping(value = "/district/delete/{id}")
    public void deleteDistrict(@PathVariable Integer id){
        districtService.deleteDistrict(id);
    }

    @PutMapping(value = "/district/update")
    public void updateDistrict(@RequestBody District district){
        districtService.updateDistrict(district);
    }
}
