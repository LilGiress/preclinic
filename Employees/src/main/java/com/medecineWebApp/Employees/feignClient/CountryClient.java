package com.medecineWebApp.Employees.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "api-gateway")
public interface CountryClient {
//    @GetMapping("/api/Configuration/patient/{id}")
//    Patient getPatient(@PathVariable Long id);

}
