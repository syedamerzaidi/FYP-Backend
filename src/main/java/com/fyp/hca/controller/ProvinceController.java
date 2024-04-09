package com.fyp.hca.controller;

import com.fyp.hca.entity.Province;
import com.fyp.hca.services.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/province")
public class ProvinceController {

    private final ProvinceService provinceService;

    @Autowired
    public ProvinceController(ProvinceService provinceService) {
        this.provinceService = provinceService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addProvince(@RequestBody Province province) {
        try {
            provinceService.addProvince(province);
            return ResponseEntity.status(HttpStatus.CREATED).body("Province added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while adding province");
        }
    }

    @GetMapping("/get")
    public ResponseEntity<List<Province>> getProvince() {
        List<Province> provinces = provinceService.getProvince();
        return ResponseEntity.ok().body(provinces);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getProvinceById(@PathVariable Integer id) {
        Optional<Province> province = provinceService.getProvinceById(id);
        if (province.isPresent()) {
            return ResponseEntity.ok().body(province.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Province not found");
        }
    }

    @GetMapping("/getIdAndName")
    public ResponseEntity<?> getProvinceIdAndName() {
        List<Province> provinces = provinceService.getProvinceIdAndName();
        return ResponseEntity.ok().body(provinces);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProvince(@PathVariable Integer id) {
        try {
            if (provinceService.isProvinceAssociated(id)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot delete Province. It is associated with something.");
            }
            provinceService.deleteProvince(id);
            return ResponseEntity.status(HttpStatus.OK).body("Province deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while deleting province");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateProvince(@RequestBody Province province) {
        try {
            provinceService.updateProvince(province);
            return ResponseEntity.status(HttpStatus.OK).body("Province updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while updating province");
        }
    }
}
