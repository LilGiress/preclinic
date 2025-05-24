//package com.medecineWebApp.patients.feignClient;
//
//import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.List;
//import java.util.Map;
//
//
//@FeignClient(name = "api-gateway", path = "/api/Employees", fallback = ConfigurationClientFallback.class)
//public interface DoctorClient {
//    @CircuitBreaker(name = "EmployeeClient", fallbackMethod = "fallbackGetDoctor")
//    @Cacheable(value = "doctors", key = "#id", unless = "#result == null")
//    @GetMapping("/doctor/{id}")
//    Doctor getDoctor(@PathVariable Long id);
//
//    //récupérer les médecins selon la région et la spécialité.
//    @CircuitBreaker(name = "EmployeeClient", fallbackMethod = "fallbackSearchDoctors")
//    @Cacheable(value = "searchDoctors", key = "#region + #city + #specialty", unless = "#result == null || #result.isEmpty()")
//    @GetMapping("/search")
//    List<Map<String, String>> searchDoctors(@RequestParam String region,
//                                            @RequestParam String city,
//                                            @RequestParam String specialty);
//}
