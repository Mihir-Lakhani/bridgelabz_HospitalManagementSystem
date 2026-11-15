package com.example.HospitalManagementSystem.controller;

import com.example.HospitalManagementSystem.dto.PatientRequestDto;
import com.example.HospitalManagementSystem.dto.PatientResponseDto;
import com.example.HospitalManagementSystem.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<PatientResponseDto> createPatient(@Valid @RequestBody PatientRequestDto request){
        PatientResponseDto createdPatient = patientService.createPatient(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdPatient);
    }

    @GetMapping(params = "id")
    public ResponseEntity<PatientResponseDto> getPatient(@RequestParam Long id){
        PatientResponseDto patientFound = patientService.getPatient(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(patientFound);
    }

    @GetMapping(params = "!id")
    public ResponseEntity<List<PatientResponseDto>> getAllPatient(){
        List<PatientResponseDto> patients = patientService.getAllPatient();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(patients);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDto> updatePatient(
            @PathVariable Long id,
            @Valid @RequestBody PatientRequestDto request){
        PatientResponseDto updatedPatient = patientService.updatePatient(id, request);

        return ResponseEntity
                .status(HttpStatus.OK)
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
                .body("Patient " + id + " hard deleted successfully");

    }
}
