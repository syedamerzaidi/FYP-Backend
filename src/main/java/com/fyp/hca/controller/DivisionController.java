package com.fyp.hca.controller;


import com.fyp.hca.entity.District;
import com.fyp.hca.entity.Division;
import com.fyp.hca.services.DivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DivisionController {

    @Autowired
    DivisionService divisionService;

    @PostMapping(value = "/division/add")
    public void addDivision(@RequestBody Division division){
        divisionService.addDivision(division);
    }

    @GetMapping(value = "/division/get")
    public List<Division> getDivision(){
        return divisionService.getDivision();
    }

    @GetMapping(value = "/division/get/{id}")
    public Optional<Division> getDivisionById(@PathVariable Integer id){
        return divisionService.getDivisionById(id);
    }

    @GetMapping(value = "/division/getIdAndName")
    public List<Division> getDivisionIdAndName(){
        return divisionService.getDivisionIdAndName();
    }

    @DeleteMapping(value = "/division/delete/{id}")
    public void deleteDivision(@PathVariable Integer id){
        divisionService.deleteDivision(id);
    }

    @PutMapping(value = "/division/update")
    public void updateDivision(@RequestBody Division division){
        divisionService.updateDivision(division);
    }
}
