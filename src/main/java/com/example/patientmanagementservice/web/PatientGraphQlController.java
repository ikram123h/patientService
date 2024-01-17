package com.example.patientmanagementservice.web;


import com.example.patientmanagementservice.dtos.PatientDto;
import com.example.patientmanagementservice.entites.Patient;
import com.example.patientmanagementservice.mapper.PatientMapper;
import com.example.patientmanagementservice.repositories.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor

public class PatientGraphQlController {
    private PatientRepository patientRepository;

    private PatientMapper patientMapper;
    @QueryMapping
    public List<Patient> allPatients() { return patientRepository.findAll(); }
    @QueryMapping
    public Patient patientById(@Argument Long id) {
        Patient patient = patientRepository.findById(id).orElse(null);
        if(patient==null) throw new RuntimeException(String.format("Patient %s not found",id));

        return patient; }
    @MutationMapping
    public Patient savePatient (@Argument PatientDto patient){
        Patient c = patientMapper.fromPatientDtoToPatienEntity(patient);
        return patientRepository.save(c);
    }
}