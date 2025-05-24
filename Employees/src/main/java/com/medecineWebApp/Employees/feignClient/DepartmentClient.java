package com.medecineWebApp.Employees.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "api-gateway")
public interface DepartmentClient {
//    @GetMapping("/api/Configuration/departement/{id}")
//    Departement getDepartment(@PathVariable Long id);
}
