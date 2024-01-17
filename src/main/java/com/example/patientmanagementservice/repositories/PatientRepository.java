package com.example.patientmanagementservice.repositories;

import com.example.patientmanagementservice.entites.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
