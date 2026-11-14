package com.example.HospitalManagementSystem.entity;

import com.example.HospitalManagementSystem.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @Max(120)
    @Min(1)
    private Integer age;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String disease;

    @Column(nullable = false, unique = true)
    private String phonenumber;

    private Boolean isDeleted;
}