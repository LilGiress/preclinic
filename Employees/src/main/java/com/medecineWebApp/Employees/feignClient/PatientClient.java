package com.medecineWebApp.Employees.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "api-gateway")
public interface PatientClient {
//    @GetMapping("/api/Patients/patients/{id}")
//    Patient getPatientById(@PathVariable("id") Long id);
}
