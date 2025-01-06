package com.medecineWebApp.Employees.config;

import com.medecineWebApp.Employees.models.Patient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "patients-microservice", url = "http://localhost:7004")
public interface EmployeeClient {
    @GetMapping("/patient/{id}")
    Patient getPatient(@PathVariable Long id);

}
