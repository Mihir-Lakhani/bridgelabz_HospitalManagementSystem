package com.example.HospitalManagementSystem.service;

import com.example.HospitalManagementSystem.dto.PatientRequestDto;
import com.example.HospitalManagementSystem.dto.PatientResponseDto;
import com.example.HospitalManagementSystem.dto.mapping.PatientRequestDtoToPatient;
import com.example.HospitalManagementSystem.dto.mapping.PatientToPatientResponseDto;
import com.example.HospitalManagementSystem.entity.Patient;
import com.example.HospitalManagementSystem.exception.RecordNotFoundException;
import com.example.HospitalManagementSystem.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PatientService {

    private PatientRepository patientRepository;
    private PatientRequestDtoToPatient requestMapper;
    private PatientToPatientResponseDto responseMapper;

    public PatientService(PatientRepository patientRepository,
                          PatientRequestDtoToPatient requestMapper,
                          PatientToPatientResponseDto responseMapper) {
        this.patientRepository = patientRepository;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
    }

    public PatientResponseDto createPatient(PatientRequestDto request){
        Patient patient = requestMapper.mapToPatient(request);
        patient.setIsDeleted(false);
        return responseMapper.mapToResponseDto(patientRepository.save(patient));
    }

    public PatientResponseDto getPatient(Long id){
        Patient patient = patientRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new RecordNotFoundException("Patient not found"));
        return responseMapper.mapToResponseDto(patient);
    }

    public List<PatientResponseDto> getAllPatient(){
        List<Patient> patients = patientRepository.findByIsDeletedFalse();
        if (patients.isEmpty()) throw new RecordNotFoundException("Patients not found");

        return patients.stream().map(responseMapper::mapToResponseDto).toList();
    }

    public PatientResponseDto updatePatient(Long id, PatientRequestDto request){
        Patient patient = patientRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new RecordNotFoundException("Patient does not exist. Please create a new record"));

        requestMapper.copyToPatient(request, patient);
        return responseMapper.mapToResponseDto(patientRepository.save(patient));
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

