package com.medecineWebApp.patients.controller;

import com.medecineWebApp.patients.dto.TreatmentDTO;
import com.medecineWebApp.patients.enums.TreatmentStatus;
import com.medecineWebApp.patients.models.Treatment;
import com.medecineWebApp.patients.service.TreatmentService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/treatments")
public class TreatmentController {
    private final TreatmentService treatmentService;

    public TreatmentController(TreatmentService treatmentService) {
        this.treatmentService = treatmentService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<TreatmentDTO>> getTreatmentById(@PathVariable Long id) {
        return ResponseEntity.ok(treatmentService.getTreatment(id));
    }

    @PutMapping
    public ResponseEntity<TreatmentDTO> updateTreatmentById(@RequestParam Long id, @RequestBody Treatment treatment) {
        return ResponseEntity.ok(treatmentService.updateTreatment(id, treatment));
    }
    @DeleteMapping
    public void deleteTreatmentById(@RequestParam Long id) {
        treatmentService.deleteTreatment(id);
    }

    @PostMapping
    public ResponseEntity<TreatmentDTO> createTreatment(@RequestBody Treatment treatment) {
        return ResponseEntity.ok(treatmentService.createTreatment(treatment));
    }

    @GetMapping
    public ResponseEntity<Page<TreatmentDTO>> getTreatments(
            @RequestParam Long patientId,
            @RequestParam Long doctorId,
            @RequestParam TreatmentStatus status,
            @RequestParam String description,
            @RequestParam LocalDate date,
            @RequestParam Integer page,
            @RequestParam Integer size) {
        return ResponseEntity.ok(treatmentService.getAllTreatmentsByPatientId(patientId, doctorId, status, description, date, page, size));

    }

    @GetMapping("/medical-record/{medicalRecordId}")
    public ResponseEntity<List<TreatmentDTO>> getTreatmentsByMedicalRecordId(@PathVariable Long medicalRecordId) {
        return ResponseEntity.ok(treatmentService.findTreatmentsByMedicalRecordId(medicalRecordId));
    }
}
