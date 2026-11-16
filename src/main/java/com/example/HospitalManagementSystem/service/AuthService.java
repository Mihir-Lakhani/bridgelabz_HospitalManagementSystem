package com.example.HospitalManagementSystem.service;

import com.example.HospitalManagementSystem.dto.UserRegistrationRequestDto;
import com.example.HospitalManagementSystem.dto.UserRegistrationResponseDto;
import com.example.HospitalManagementSystem.dto.mapping.PatientRequestDtoToPatient;
import com.example.HospitalManagementSystem.dto.mapping.PatientToPatientResponseDto;
import com.example.HospitalManagementSystem.dto.mapping.UserRegistrationRequestDtoToUser;
import com.example.HospitalManagementSystem.dto.mapping.UserToUserRegistrationResponseDto;
import com.example.HospitalManagementSystem.entity.User;
import com.example.HospitalManagementSystem.enums.RoleType;
import com.example.HospitalManagementSystem.exception.UsernameAlreadyTakenException;
import com.example.HospitalManagementSystem.repository.RoleRepository;
import com.example.HospitalManagementSystem.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private UserRegistrationRequestDtoToUser requestMapper;
    private UserToUserRegistrationResponseDto responseMapper;

    public AuthService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, UserRegistrationRequestDtoToUser requestMapper, UserToUserRegistrationResponseDto responseMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
        this.roleRepository = roleRepository;
    }

    public UserRegistrationResponseDto register(UserRegistrationRequestDto userRegistrationRequest){

        if (!userRepository.findByUsername(userRegistrationRequest.getUsername()).isEmpty()){
            throw new UsernameAlreadyTakenException("This user name already exists please LogIn or change Username");
        }
        if (!userRepository.findByEmail(userRegistrationRequest.getEmail()).isEmpty()){
            throw new DataIntegrityViolationException("This email address already exists please LogIn or change email");
        }

        String encodedPassword = passwordEncoder.encode(userRegistrationRequest.getPassword());

        User userToBeSaved = requestMapper.mapToUser(userRegistrationRequest);
        userToBeSaved.setPassword(encodedPassword);
        userToBeSaved.getRoles().add(
                roleRepository.findByName(RoleType.USER)
                        .orElseThrow(() -> new IllegalStateException("Role is missing")));

        return responseMapper.mapToResponseDto(userRepository.save(userToBeSaved));


    }
}
