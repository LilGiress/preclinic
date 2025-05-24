//package com.medecineWebApp.patients.feignClient;
//
//import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
//
//@FeignClient(name = "api-gateway", path = "api/Employees", fallback = ConfigurationClientFallback.class)
//public interface AppointmentClient {
//    @CircuitBreaker(name = "AppointmentClient", fallbackMethod = "fallbackGetAppointment")
//    @Cacheable(value = "appointments", key = "#id", unless = "#result == null")
//    @GetMapping("/appointments/{id}")
//    Appointment getAppointment(@PathVariable Long id);
//}
