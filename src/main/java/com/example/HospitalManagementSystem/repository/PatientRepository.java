package com.example.HospitalManagementSystem.repository;

import com.example.HospitalManagementSystem.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    public Optional<Patient> findByIdAndIsDeletedFalse(Long id);

    public List<Patient> findByIsDeletedFalse();
}