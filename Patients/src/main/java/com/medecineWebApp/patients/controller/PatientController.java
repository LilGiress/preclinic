package com.medecineWebApp.patients.controller;

import com.medecineWebApp.patients.dto.PatientDTO;
import com.medecineWebApp.patients.models.Patient;
import com.medecineWebApp.patients.service.PatientService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }
    @PostMapping
    public ResponseEntity<PatientDTO> createPatient(@RequestBody Patient patient) {
        return ResponseEntity.ok(patientService.createPatient(patient));

    }
    @GetMapping("/all")
    public ResponseEntity<Page<Patient>> getAllPatients(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<Patient> patients = patientService.getAllPatients(page, size);
        return ResponseEntity.ok(patients);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> getPatient(@PathVariable Long id) {
        return ResponseEntity.ok(patientService.getPatient(id));
    }
    @PutMapping("/update")
    public ResponseEntity<PatientDTO> updatePatient(@RequestParam Long id,@RequestBody Patient patient) {
        return ResponseEntity.ok(patientService.updatePatient(id,patient));
    }

    @DeleteMapping("/delete")
    public void deletePatient(@RequestParam Long id) {
        patientService.deletePatient(id);
    }
}
