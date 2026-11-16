package com.example.HospitalManagementSystem.dto.mapping;

import com.example.HospitalManagementSystem.dto.UserRegistrationRequestDto;
import com.example.HospitalManagementSystem.entity.User;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserRegistrationRequestDtoToUser {

    public User mapToUser(UserRegistrationRequestDto request) {
        User user = new User();
        copyToUser(request, user);
        return user;
    }

    public void copyToUser(UserRegistrationRequestDto request, User user) {
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
    }
}
