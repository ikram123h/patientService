package com.example.patientmanagementservice.configuration;

import com.example.patientmanagementservice.web.PatientGraphQlController;
import lombok.AllArgsConstructor;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@AllArgsConstructor
@Configuration

public class CxfConfig {
    private Bus bus;
    private PatientGraphQlController patientSoapService;

    @Bean
    public EndpointImpl endpoint(){
        EndpointImpl endpoint=new EndpointImpl(bus,patientSoapService);
        endpoint.publish("/PatientManagementService");
        return endpoint;
    }
}
