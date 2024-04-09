package com.fyp.hca.controller;

import com.fyp.hca.entity.Disease;
import com.fyp.hca.services.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disease")
public class DiseaseController {
    private final DiseaseService diseaseService;

    @Autowired
    public DiseaseController(DiseaseService diseaseService) {
        this.diseaseService = diseaseService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addDisease(@RequestBody Disease disease) {
        try {
            diseaseService.addDisease(disease);
            return ResponseEntity.status(HttpStatus.CREATED).body("Disease added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while adding disease");
        }
    }

    @GetMapping("/get")
    public ResponseEntity<List<Disease>> getDisease() {
        List<Disease> diseases = diseaseService.getDiseases();
        return ResponseEntity.ok().body(diseases);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDisease(@PathVariable Integer id) {
        try {
            if (diseaseService.isDiseaseAssociated(id)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot delete Disease. It is associated with patients.");
            }
            diseaseService.deleteDisease(id);
            return ResponseEntity.status(HttpStatus.OK).body("Disease deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while deleting disease");
        }
    }


    @PutMapping("/update")
    public ResponseEntity<String> updateDisease(@RequestBody Disease disease) {
        try {
            diseaseService.updateDisease(disease);
            return ResponseEntity.status(HttpStatus.OK).body("Disease updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while updating disease");
        }
    }
}
