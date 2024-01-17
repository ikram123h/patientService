package com.example.patientmanagementservice.web;

import com.example.patientmanagementservice.dtos.PatientDto;
import com.example.patientmanagementservice.entites.Patient;
import com.example.patientmanagementservice.mapper.PatientMapper;
import com.example.patientmanagementservice.repositories.PatientRepository;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
@WebService
public class PatientSoapService {
    private PatientRepository patientRepository;
    private PatientMapper patientMapper;

    @WebMethod
    public List<Patient> allPatients() {
        return patientRepository.findAll();
    }

    @WebMethod
    public Patient patientById(@WebParam Long id) {
        return patientRepository.findById(id).get();
    }

    @WebMethod
    public Patient savePatient(@WebParam Patient patient) {
        patientRepository.save(patient);
        return patient;
    }
    @WebMethod
    public Patient updatePatient(@WebParam(name = "patient") PatientDto patientDto){

        Patient patient=patientMapper.fromPatientDtoToPatienEntity(patientDto);
        return patientRepository.save(patient);
    }
    @WebMethod
    public void deletePatient(@WebParam(name = "id") Long id){
        patientRepository.deleteById(id);
    }
}


