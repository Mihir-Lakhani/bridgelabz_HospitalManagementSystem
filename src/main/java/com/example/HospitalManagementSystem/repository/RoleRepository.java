package com.example.HospitalManagementSystem.repository;

import com.example.HospitalManagementSystem.entity.Role;
import com.example.HospitalManagementSystem.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

        public Optional<Role> findByName(RoleType name);

}