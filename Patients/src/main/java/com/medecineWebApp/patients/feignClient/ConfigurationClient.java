package com.medecineWebApp.patients.feignClient;


import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;
@CircuitBreaker(name = "ConfigurationClient", fallbackMethod = "fallbackGetEmployee")
@Cacheable(value = "employees", key = "#id")
@FeignClient(name = "api-gateway")
public interface ConfigurationClient {
   /* @GetMapping("/api/Configuration/departement/{id}")
    Departement getDepartementById(@PathVariable Long id);*/

    @GetMapping("api/Configuration/regions")
    List<Map<String, String>> getRegions(); // Retourne une liste de régions avec leurs villes

    @GetMapping("api/Configuration/services")
    List<String> getMedicalServices(); // Liste des services médicaux



}
