package com.medecineWebApp.Employees.config;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "configuration-microservice", url = "http://localhost:7001")
public interface departmentClient {
}
