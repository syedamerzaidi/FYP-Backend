package com.fyp.hca.controller;

import com.fyp.hca.entity.Patient;
import com.fyp.hca.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patient")
public class PatientController {
    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }
    @PostMapping("/add")
    public ResponseEntity<String> addPatient(@RequestBody Patient patient) {
        try {
            patientService.addPatient(patient);
            return ResponseEntity.status(HttpStatus.CREATED).body("Patient added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while adding patient");
        }
    }

    @GetMapping("/get")
    public ResponseEntity<List<Patient>> getPatients() {
        List<Patient> patients = patientService.getPatients();
        return ResponseEntity.ok().body(patients);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getPatientById(@PathVariable Integer id) {
        Optional<Patient> patient = patientService.getPatientById(id);
        if (patient.isPresent()) {
            return ResponseEntity.ok().body(patient.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Patient not found");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Integer id) {
        try {
            patientService.deletePatient(id);
            return ResponseEntity.status(HttpStatus.OK).body("Patient deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while deleting patient");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updatePatient(@RequestBody Patient patient) {
        try {
            patientService.updatePatient(patient);
            return ResponseEntity.status(HttpStatus.OK).body("Patient updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while updating patient");
        }
    }
}
