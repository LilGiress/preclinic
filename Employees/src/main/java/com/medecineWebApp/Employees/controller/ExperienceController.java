package com.medecineWebApp.Employees.controller;

import com.medecineWebApp.Employees.dto.ExperienceDTO;
import com.medecineWebApp.Employees.models.Experience;
import com.medecineWebApp.Employees.models.doctors.Doctor;
import com.medecineWebApp.Employees.services.ExperienceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/experiences")
public class ExperienceController {
    private final ExperienceService experienceService;

    public ExperienceController(ExperienceService experienceService) {
        this.experienceService = experienceService;
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<ExperienceDTO>>  getExperiencesByDoctorId(@PathVariable Doctor doctorId) {
        return ResponseEntity.ok(experienceService.getAllExperiencesByDoctorId(doctorId));
    }

    @PostMapping
    public ResponseEntity<ExperienceDTO> addExperience(@RequestBody Experience experience) {
        return ResponseEntity.ok(experienceService.saveExperience(experience));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExperience(@PathVariable Long id) {
        experienceService.deleteExperience(id);
        return ResponseEntity.noContent().build();
    }
}
