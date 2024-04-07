package com.fyp.hca.controller;

import com.fyp.hca.entity.Tehsil;
import com.fyp.hca.services.TehsilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class TehsilController {
    @Autowired
    TehsilService tehsilService;

    @PostMapping(value = "/tehsil/add")
    public void addTehsil(@RequestBody Tehsil tehsil){
        tehsilService.addTehsil(tehsil);
    }

    @GetMapping(value = "/tehsil/get")
    public List<Tehsil> getTehsil(){
        return tehsilService.getTehsil();
    }

    @GetMapping(value = "/tehsil/get/{id}")
    public Optional<Tehsil> getTehsilById(@PathVariable Integer id){
        return tehsilService.getTehsilById(id);
    }

    @GetMapping(value = "/tehsil/getIdAndName")
    public List<Map<String,?>> getTehsilIdAndName(){
        return tehsilService.getTehsilIdAndName();
    }


    @DeleteMapping(value = "/tehsil/delete/{id}")
    public void deleteTehsil(@PathVariable Integer id){
        tehsilService.deleteTehsil(id);
    }

    @PutMapping(value = "/tehsil/update")
    public void updateTehsil(@RequestBody Tehsil tehsil){
        tehsilService.updateTehsil(tehsil);
    }

}