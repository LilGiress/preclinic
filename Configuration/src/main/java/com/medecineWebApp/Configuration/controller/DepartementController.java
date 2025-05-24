package com.medecineWebApp.Configuration.controller;

import com.medecineWebApp.Configuration.dto.DepartementDTO;
import com.medecineWebApp.Configuration.models.Departement;
import com.medecineWebApp.Configuration.service.DepartementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/departement")
public class DepartementController {
    private final DepartementService departementService;

    public DepartementController(DepartementService departementService) {
        this.departementService = departementService;
    }
    @GetMapping
    public ResponseEntity<List<DepartementDTO>> getAllDepartements() {
        return ResponseEntity.ok(departementService.getAllDepartements());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<DepartementDTO>> getDepartementById(@PathVariable Long id) {
        return ResponseEntity.ok(departementService.getDepartementById(id));
    }
    @PostMapping
    public ResponseEntity<DepartementDTO> createDepartement(@RequestBody Departement departement) {
        return ResponseEntity.ok(departementService.createDepartement(departement));
    }
    @PutMapping
    public ResponseEntity<DepartementDTO> updateDepartement(@RequestParam Long id,@RequestBody Departement departement) {
        return ResponseEntity.ok(departementService.updateDepartement(id, departement));
    }
    @DeleteMapping
    public void deleteDepartementById(@RequestParam Long id) {
        departementService.deleteDepartement(id);
    }
}
