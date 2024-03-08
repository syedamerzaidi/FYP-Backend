package com.fyp.hca.controller;

import com.fyp.hca.entity.Disease;
import com.fyp.hca.entity.Tehsil;
import com.fyp.hca.entity.Users;
import com.fyp.hca.services.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DiseaseController {
    @Autowired
    private DiseaseService diseaseService;

    @PostMapping(value = "/disease/add")
    public void addDisease(@RequestBody Disease disease){
        diseaseService.addDisease(disease);
    }

    @GetMapping(value = "disease/get")
    public List<Disease> getDisease(){
        return diseaseService.getDiseases();
    }


    @DeleteMapping(value = "/disease/delete/{id}")
    public void deleteDisease(@PathVariable Integer id){
        diseaseService.deleteDisease(id);
    }

    @PutMapping(value = "/disease/update")
    public void updateDisease(@RequestBody Disease disease){
        diseaseService.updateDisease(disease);
    }

}
