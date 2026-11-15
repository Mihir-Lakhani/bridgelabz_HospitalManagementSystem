package com.example.HospitalManagementSystem.repository;

import com.example.HospitalManagementSystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;


public interface UserRepository extends JpaRepository<User, Long> {

}