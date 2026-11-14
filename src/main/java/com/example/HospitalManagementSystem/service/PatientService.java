package com.example.HospitalManagementSystem.service;

import com.example.HospitalManagementSystem.entity.Patient;
import com.example.HospitalManagementSystem.exception.RecordNotFoundException;
import com.example.HospitalManagementSystem.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PatientService {

    private PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient createPatient(Patient patient){

        Patient patientSaved = patientRepository.save(patient);

        return patientSaved;
    }

    public Patient getPatient(Long id){

        Patient patientGot = patientRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Patient not found"));

        return patientGot;
    }

    public List<Patient> getAllPatient(){

        List<Patient> patients = patientRepository.findAll();
        if (patients.isEmpty()) throw new RecordNotFoundException("Patients not found");

        return patients;
    }
}