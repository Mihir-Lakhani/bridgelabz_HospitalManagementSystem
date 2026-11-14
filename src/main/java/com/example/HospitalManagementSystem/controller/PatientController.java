package com.example.HospitalManagementSystem.controller;

import com.example.HospitalManagementSystem.entity.Patient;
import com.example.HospitalManagementSystem.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public ResponseEntity<Patient> createPatient(@Valid @RequestBody Patient patient){

        Patient createdPatient = patientService.createPatient(patient);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdPatient);
    }

    @GetMapping(params = "id")
    public ResponseEntity<Patient> getPatient(@RequestParam Long id){

        Patient Patientfound = patientService.getPatient(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Patientfound);
    }

    @GetMapping(params = "!id")
    public ResponseEntity<List<Patient>> getAllPatient(){

        List<Patient> patients = patientService.getAllPatient();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(patients);
    }

    @PutMapping
    public ResponseEntity<Patient> updatePatient(@RequestBody Patient patient){

        Patient updatedPatient = patientService.updatePatient(patient);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(updatedPatient);
    }

    @DeleteMapping(params = "id")
    public ResponseEntity<String> softDeletePatient(@RequestParam Long id){

        patientService.softDeletePatient(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Patient " + id + " deleted successfully");

    }

    @DeleteMapping(params = "id", value = "/harddelete")
    public ResponseEntity<String> hardDeletePatient(@RequestParam Long id){

        patientService.hardDeletePatient(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Patient " + id + "hard deleted successfully");

    }
}