package com.example.HospitalManagementSystem.dto.mapping;

import com.example.HospitalManagementSystem.dto.UserRegistrationResponseDto;
import com.example.HospitalManagementSystem.entity.Patient;
import com.example.HospitalManagementSystem.entity.User;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserToUserRegistrationResponseDto {

    public UserRegistrationResponseDto mapToResponseDto(User user) {
        UserRegistrationResponseDto response = new UserRegistrationResponseDto();

        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setRoles(new HashSet<>(user.getRoles()));

        return response;
    }
}
