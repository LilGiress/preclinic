package com.medecineWebApp.Configuration.controller;

import com.medecineWebApp.Configuration.dto.ServicesDTO;
import com.medecineWebApp.Configuration.models.Services;
import com.medecineWebApp.Configuration.service.ServiceService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
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
            @RequestParam(name = "size",defaultValue = "1000000") int size
    ) {
        return ResponseEntity.ok(serviceService.getAllServices(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicesDTO> getServiceById(@PathVariable Long id) {
        return ResponseEntity.ok(serviceService.getService(id));

    }

    @GetMapping("/service-departement/{departementId}")
    public ResponseEntity<List<ServicesDTO>> getServiceByDepartement(@PathVariable Long departementId) {
        return ResponseEntity.ok(serviceService.getAllServicesByDepartementId(departementId));

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ServicesDTO> updateService(
            @PathVariable Long id,
            @RequestBody Services services
    ){
        return ResponseEntity.ok(serviceService.updateService(id, services));

    }

    @DeleteMapping("/{id}")
    public void deleteServiceById(@PathVariable Long id) {
        serviceService.deleteService(id);
    }


}
