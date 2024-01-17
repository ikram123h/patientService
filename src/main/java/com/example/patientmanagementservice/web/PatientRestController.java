package com.example.patientmanagementservice.web;


import com.example.patientmanagementservice.entites.Patient;
import com.example.patientmanagementservice.repositories.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class PatientRestController {

    private PatientRepository patientRepository;

    @GetMapping("/patients")
    public List<Patient> patientList() {
        return patientRepository.findAll();
    }

    @GetMapping("/patients/{id}")
    public Patient patientById(@PathVariable Long id) {
        return patientRepository.findById(id).get();
    }

    @PostMapping("/patients")
    public Patient patientById(@RequestBody Patient patient) {
        patientRepository.save(patient);
        return patient;
    }
}