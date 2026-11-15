package com.example.HospitalManagementSystem.dto;

import com.example.HospitalManagementSystem.enums.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class PatientResponseDto {

    private Long id;

    private String name;

    private Integer age;

    private Gender gender;

    private String disease;

    private String address;

    private String phoneNumber;
}
