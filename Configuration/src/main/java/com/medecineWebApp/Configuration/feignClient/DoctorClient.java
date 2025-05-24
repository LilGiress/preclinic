package com.medecineWebApp.Configuration.feignClient;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "api-gateway")
public interface DoctorClient {
//    @GetMapping("api/Employees/doctor/{id}")
//    Doctor getDoctor(@PathVariable Long id);
}
