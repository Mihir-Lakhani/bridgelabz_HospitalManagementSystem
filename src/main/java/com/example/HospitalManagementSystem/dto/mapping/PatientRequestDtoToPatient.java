package com.example.HospitalManagementSystem.dto.mapping;

import com.example.HospitalManagementSystem.dto.PatientRequestDto;
import com.example.HospitalManagementSystem.entity.Patient;
import org.springframework.stereotype.Component;

@Component
public class PatientRequestDtoToPatient {

    public Patient mapToPatient(PatientRequestDto request) {
        Patient patient = new Patient();
        copyToPatient(request, patient);
        return patient;
    }

    public void copyToPatient(PatientRequestDto request, Patient patient) {
        patient.setName(request.getName());
        patient.setAge(request.getAge());
        patient.setGender(request.getGender());
        patient.setDisease(request.getDisease());
        patient.setAddress(request.getAddress());
        patient.setPhoneNumber(request.getPhoneNumber());
    }
}
