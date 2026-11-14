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

        patient.setIsDeleted(false);
        Patient patientSaved = patientRepository.save(patient);

        return patientSaved;
    }

    public Patient getPatient(Long id){

        Patient patientGot = patientRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new RecordNotFoundException("Patient not found"));

        return patientGot;
    }

    public List<Patient> getAllPatient(){

        List<Patient> patients = patientRepository.findByIsDeletedFalse();
        if (patients.isEmpty()) throw new RecordNotFoundException("Patients not found");

        return patients;
    }

    public Patient updatePatient(Patient newPatient){

        Long id = newPatient.getId();

        Patient oldPatient = patientRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new RecordNotFoundException("Patient does not exist. Please create a new record"));

        oldPatient.setAge(newPatient.getAge());
        oldPatient.setDisease(newPatient.getDisease());
        oldPatient.setGender(newPatient.getGender());
        oldPatient.setName(newPatient.getName());
        oldPatient.setPhonenumber(newPatient.getPhonenumber());

        Patient newPatient2 = patientRepository.save(oldPatient);

        return newPatient2;

    }

    public Boolean softDeletePatient(Long id){

        Patient patient = patientRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new RecordNotFoundException("Patient to be deleted was not found"));
        patient.setIsDeleted(true);
        patientRepository.save(patient);

        return true;
    }

    public Boolean hardDeletePatient(Long id){

        Patient patient = patientRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new RecordNotFoundException("Patient to be deleted was not found"));
        patientRepository.delete(patient);
        return true;
    }
}

