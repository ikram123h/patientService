package com.example.patientmanagementservice;

import com.example.patientmanagementservice.entites.Patient;
import com.example.patientmanagementservice.repositories.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PatientManagementServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientManagementServiceApplication.class, args);
    }
@Bean
    CommandLineRunner start(PatientRepository patientRepository){
        return args -> {
            patientRepository.save(Patient.builder().name("zineb").email("zineb@gmail.com").description_diagnostic("Check-up").build());
            patientRepository.save(Patient.builder().name("ahmed").email("ahmed@gmail.com").description_diagnostic("Scanner").build());
            patientRepository.save(Patient.builder().name("ayoub").email("ayoub@gmail.com").description_diagnostic("medical analysis").build());
            patientRepository.save(Patient.builder().name("rayane").email("rayane@gmail.com").description_diagnostic("Check-up").build());

        };
}
}
