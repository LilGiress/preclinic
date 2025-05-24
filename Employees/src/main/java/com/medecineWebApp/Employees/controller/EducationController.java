package com.medecineWebApp.Employees.controller;

import com.medecineWebApp.Employees.dto.EducationDTO;
import com.medecineWebApp.Employees.models.Education;
import com.medecineWebApp.Employees.models.doctors.Doctor;
import com.medecineWebApp.Employees.services.EducationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/educations")
public class EducationController {
    private final EducationService educationService;

    public EducationController(EducationService educationService) {
        this.educationService = educationService;
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<EducationDTO>>  getEducationByDoctorId(@PathVariable Doctor doctorId) {
        return ResponseEntity.ok(educationService.getAllEducationsByDoctorId(doctorId));
    }

    @PostMapping
    public ResponseEntity<EducationDTO>  addEducation(@RequestBody Education education) {
        return ResponseEntity.ok(educationService.saveEducation(education));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEducation(@PathVariable Long id) {
        educationService.deleteEducationById(id);
        return ResponseEntity.noContent().build();
    }
}
