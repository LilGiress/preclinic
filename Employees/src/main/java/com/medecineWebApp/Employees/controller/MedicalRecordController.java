package com.medecineWebApp.Employees.controller;

import com.medecineWebApp.Employees.dto.MedicalRecordDTO;
import com.medecineWebApp.Employees.models.MedicalRecord;
import com.medecineWebApp.Employees.services.MedicalRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medical-records")
public class MedicalRecordController {
    private final MedicalRecordService medicalRecordService;

    public MedicalRecordController(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }
    @PostMapping("/create")
    public ResponseEntity<MedicalRecordDTO> createMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        return ResponseEntity.ok(medicalRecordService.save(medicalRecord));
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<MedicalRecordDTO>> getAllMedicalRecordByDoctorId(@PathVariable Long doctorId) {
        return ResponseEntity.ok(medicalRecordService.findMedicalRecordByDoctorId(doctorId));
    }


    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<MedicalRecordDTO>> getAllMedicalRecordByPatientId(@PathVariable Long patientId) {
        return ResponseEntity.ok(medicalRecordService.findMedicalRecordsByPatientId(patientId));
    }
}
