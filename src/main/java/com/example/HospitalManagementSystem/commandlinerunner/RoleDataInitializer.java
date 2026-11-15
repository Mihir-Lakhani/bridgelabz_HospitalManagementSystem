package com.example.HospitalManagementSystem.commandlinerunner;

import com.example.HospitalManagementSystem.entity.Role;
import com.example.HospitalManagementSystem.enums.RoleType;
import com.example.HospitalManagementSystem.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class RoleDataInitializer implements CommandLineRunner {

    private RoleRepository roleRepository;

    public RoleDataInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args){
        for (RoleType roleType : RoleType.values()){
            roleRepository.findByName(roleType)
                    .orElseGet(() ->
                            roleRepository.save(new Role(null, roleType)));
        }
    }
}