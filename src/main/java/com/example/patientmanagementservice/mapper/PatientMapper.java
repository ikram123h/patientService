package com.example.patientmanagementservice.mapper;

import com.example.patientmanagementservice.dtos.PatientDto;
import com.example.patientmanagementservice.entites.Patient;
import com.example.patientmanagementservice.stub.PatientServiceOuterClass;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class PatientMapper {
    private ModelMapper modelMapper=new ModelMapper();
    public Patient fromPatientDtoToPatienEntity(PatientDto patientDto){

        Patient patient=modelMapper.map(patientDto,Patient.class);
        return patient;

    }

    public PatientServiceOuterClass.Patient fromPatientEntityToPatientGrpc(Patient patient) {
        System.out.println(patient);
        PatientServiceOuterClass.Patient grpcPatient;
        grpcPatient = modelMapper.map(patient, PatientServiceOuterClass.Patient.Builder.class).build();
        System.out.println(grpcPatient);
        return grpcPatient;
    }


    public Patient fromGrpcPatientRequestToPatientEntity(PatientServiceOuterClass.PatientRequest patientRequest) {
        Patient patient = modelMapper.map(patientRequest, Patient.class);
        return patient;
    }

}
