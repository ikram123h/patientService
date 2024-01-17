package com.example.patientmanagementservice.web;

import com.example.patientmanagementservice.entites.Patient;
import com.example.patientmanagementservice.mapper.PatientMapper;
import com.example.patientmanagementservice.repositories.PatientRepository;
import com.example.patientmanagementservice.stub.PatientServiceGrpc;
import com.example.patientmanagementservice.stub.PatientServiceOuterClass;
import net.devh.boot.grpc.server.service.GrpcService;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;
@GrpcService
public class PatientGrpcService  extends PatientServiceGrpc.PatientServiceImplBase {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private PatientMapper patientMapper;
    @Override
    public void getAllPatients(PatientServiceOuterClass.GetAllPatientsRequest request, StreamObserver<PatientServiceOuterClass.GetAllPatientsResponse> responseObserver){
        List<Patient> patientList = patientRepository.findAll();
        List<PatientServiceOuterClass.Patient> grpcPatients =
                patientList.stream()
                        .map(patientMapper::fromPatientEntityToPatientGrpc).collect(Collectors.toList());

        PatientServiceOuterClass.GetAllPatientsResponse patientsResponse =
                PatientServiceOuterClass.GetAllPatientsResponse.newBuilder()
                        .addAllPatients(grpcPatients)
                        .build();
        responseObserver.onNext(patientsResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void getPatientById(PatientServiceOuterClass.GetPatientByIdRequest request, StreamObserver<PatientServiceOuterClass.GetPatientByIdResponse> responseObserver){
        Patient patientEntity = patientRepository.findById(request.getPatientId()).get();
        PatientServiceOuterClass.GetPatientByIdResponse response =
                PatientServiceOuterClass.GetPatientByIdResponse.newBuilder()
                        .setPatient(patientMapper.fromPatientEntityToPatientGrpc(patientEntity))
                        .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void savePatient(PatientServiceOuterClass.SavePatientRequest request, StreamObserver<PatientServiceOuterClass.SavePatientResponse> responseObserver){
        PatientServiceOuterClass.PatientRequest patientRequest = request.getPatient();
        Patient patient= patientMapper.fromGrpcPatientRequestToPatientEntity(patientRequest);
        Patient savedPatient = patientRepository.save(patient);
        PatientServiceOuterClass.SavePatientResponse response =
                PatientServiceOuterClass.SavePatientResponse.newBuilder()
                        .setPatient(patientMapper.fromPatientEntityToPatientGrpc(savedPatient))
                        .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
