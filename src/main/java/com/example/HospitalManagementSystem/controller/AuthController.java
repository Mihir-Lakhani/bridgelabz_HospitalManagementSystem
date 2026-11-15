package com.example.HospitalManagementSystem.controller;

import com.example.HospitalManagementSystem.dto.UserRegistrationRequestDto;
import com.example.HospitalManagementSystem.dto.UserRegistrationResponseDto;
import com.example.HospitalManagementSystem.service.AuthService;
import jakarta.validation.Valid;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("api/user")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegistrationResponseDto> registerUser(
            @Valid @RequestBody UserRegistrationRequestDto userRegistrationRequest){

        UserRegistrationResponseDto responseDto = authService.register(userRegistrationRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseDto);
    }

}