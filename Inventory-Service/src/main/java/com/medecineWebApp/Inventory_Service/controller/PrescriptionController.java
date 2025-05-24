package com.medecineWebApp.Inventory_Service.controller;

import com.medecineWebApp.Inventory_Service.dto.PrescriptionDTO;
import com.medecineWebApp.Inventory_Service.models.Prescription;
import com.medecineWebApp.Inventory_Service.models.PrescriptionItem;
import com.medecineWebApp.Inventory_Service.service.PrescriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    public PrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    @PostMapping
    public ResponseEntity <PrescriptionDTO> createPrescription(@RequestBody Prescription prescription) {
        return ResponseEntity.ok( prescriptionService.createPrescription(prescription));
    }

    @GetMapping("/patient/{id}")
    public ResponseEntity <List<PrescriptionDTO>> getPrescriptionsByPatient(@PathVariable Long id) {
        return ResponseEntity.ok(prescriptionService.getPrescriptionsByPatient(id)) ;
    }

    @PostMapping("/{prescriptionId}/items")
    public ResponseEntity<PrescriptionDTO> addItemToPrescription(
            @PathVariable Long prescriptionId,
            @RequestBody PrescriptionItem item) {
        return ResponseEntity.ok(prescriptionService.addPrescriptionItem(prescriptionId, item));
    }

}
