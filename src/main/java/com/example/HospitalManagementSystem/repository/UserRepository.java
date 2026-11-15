package com.example.HospitalManagementSystem.repository;

import com.example.HospitalManagementSystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public Boolean existsByUsername(String username);

    public Boolean existsByEmail(String username);


}