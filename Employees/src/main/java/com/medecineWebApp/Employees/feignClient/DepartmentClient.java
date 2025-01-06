package com.medecineWebApp.Employees.feignClient;

import com.medecineWebApp.Employees.models.Departement;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "configuration-microservice", url = "http://localhost:7001")
public interface departmentClient {
    @GetMapping("/{id}")
    Departement getDepartment(@PathVariable Long id);
}
