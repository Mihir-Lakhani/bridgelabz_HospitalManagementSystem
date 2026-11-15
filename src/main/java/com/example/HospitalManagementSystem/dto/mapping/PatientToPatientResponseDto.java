package com.example.HospitalManagementSystem.dto.mapping;

import com.example.HospitalManagementSystem.dto.PatientResponseDto;
import com.example.HospitalManagementSystem.entity.Patient;
import org.springframework.stereotype.Component;

@Component
public class PatientToPatientResponseDto {

    public PatientResponseDto mapToResponseDto(Patient patient) {
        PatientResponseDto response = new PatientResponseDto();
        response.setId(patient.getId());
        response.setName(patient.getName());
        response.setAge(patient.getAge());
        response.setGender(patient.getGender());
        response.setDisease(patient.getDisease());
        response.setAddress(patient.getAddress());
        response.setPhoneNumber(patient.getPhoneNumber());
        return response;
    }
}
