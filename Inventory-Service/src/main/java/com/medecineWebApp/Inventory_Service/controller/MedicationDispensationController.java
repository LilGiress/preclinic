package com.medecineWebApp.Inventory_Service.controller;

import com.medecineWebApp.Inventory_Service.service.MedicationDispensationService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dispensations")
public class MedicationDispensationController {
    private final MedicationDispensationService medicationDispensationService;

    public MedicationDispensationController(MedicationDispensationService medicationDispensationService) {
        this.medicationDispensationService = medicationDispensationService;
    }
    @PostMapping("/{prescriptionId}/dispense")
    public void dispenseMedication(@PathVariable Long prescriptionId) {
        medicationDispensationService.dispenseMedication(prescriptionId);
    }
}
