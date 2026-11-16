package com.example.HospitalManagementSystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationRequestDto {

    @NotBlank
    private String username;

    @NotBlank(message = "Password is required")
    @Size(
            min = 15,
            max = 64,
            message = "Password must be between 15 and 64 characters"
    )
    private String password;

    @Email
    @NotBlank
    private String email;

}
