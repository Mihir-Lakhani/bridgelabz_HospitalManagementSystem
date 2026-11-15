package com.example.HospitalManagementSystem.dto;

import com.example.HospitalManagementSystem.enums.Gender;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class PatientRequestDto {

    @NotBlank
    private String name;

    @NotNull
    @Min(1)
    @Max(120)
    private Integer age;

    @NotNull
    private Gender gender;

    @NotBlank
    private String disease;

    @NotBlank
    private String address;

    @NotBlank
    @Pattern(regexp = "^[0-9]{10}$")
    private String phoneNumber;
}
