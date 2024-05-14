package com.fyp.hca.controller;

import com.fyp.hca.entity.Province;
import com.fyp.hca.services.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProvinceController {
    @Autowired
    ProvinceService provinceService;

    @PostMapping(value = "/province/add")
    public void addProvince(@RequestBody Province province){
        provinceService.addProvince(province);
    }

    @GetMapping(value = "/province/get")
    public List<Province> getProvince(){
        return provinceService.getProvince();
    }

    @GetMapping(value = "/province/get/{id}")
    public Optional<Province> getProvincById(@PathVariable Integer id){
        return provinceService.getProvinceById(id);
    }


    @DeleteMapping(value = "/province/delete/{id}")
    public void deleteProvince(@PathVariable Integer id){
        provinceService.deleteProvince(id);
    }

    @PutMapping(value = "/province/update")
    public void updateProvince(@RequestBody Province province){
        provinceService.updateProvince(province);
    }
}
