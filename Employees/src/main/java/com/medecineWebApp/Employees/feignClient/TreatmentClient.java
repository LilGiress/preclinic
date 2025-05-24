package com.medecineWebApp.Employees.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "api-gateway")
public interface TreatmentClient {
//    @GetMapping("/api/Patients/treatments/{id}")
//    Treatment getTreatment(@PathVariable("id") Long id);
}
