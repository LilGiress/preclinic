package com.medecineWebApp.Configuration.controller;

import com.medecineWebApp.Configuration.dto.ServicesDTO;
import com.medecineWebApp.Configuration.models.Services;
import com.medecineWebApp.Configuration.service.ServiceService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/services")
public class ServiceController {
    private final ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @PostMapping
    public ResponseEntity<ServicesDTO> createService(@RequestBody Services service) {
        return ResponseEntity.ok(serviceService.createService(service));
    }

    @GetMapping("/all")
    public ResponseEntity<Page<ServicesDTO>> getAllServices(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size",defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(serviceService.getAllServices(page, size));
    }

    @GetMapping("/service")
    public ResponseEntity<ServicesDTO> getServiceById(@RequestParam(name = "id") Long id) {
        return ResponseEntity.ok(serviceService.getService(id));

    }
    @PutMapping("/update")
    public ResponseEntity<ServicesDTO> updateService(
            @RequestParam Long id,
            @RequestBody Services services
    ){
        return ResponseEntity.ok(serviceService.updateService(id, services));

    }

    @DeleteMapping("/delete")
    public void deleteServiceById(@RequestParam(name = "id") Long id) {
        serviceService.deleteService(id);
    }


}
