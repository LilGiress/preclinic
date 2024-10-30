package com.medecineWebApp.Configuration.controller;

import com.medecineWebApp.Configuration.models.setting.SalarySettings;
import com.medecineWebApp.Configuration.service.SalarySettingsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/salary-settings")
public class SalarySettingsController {
    private final SalarySettingsService salarySettingsService;

    public SalarySettingsController(SalarySettingsService salarySettingsService) {
        this.salarySettingsService = salarySettingsService;
    }
    @PostMapping
    // Method to save or update the salary settings
    public ResponseEntity <SalarySettings> saveSalarySettings(SalarySettings salarySettings) {
        return ResponseEntity.ok(salarySettingsService.createSalarySettings(salarySettings));
    }
    //  PUT endpoint to  update the salary settings
    @PutMapping
    public ResponseEntity <SalarySettings> updateSalarySettings(@RequestParam Long id, @RequestBody SalarySettings salarySettings) {
        return ResponseEntity.ok(salarySettingsService.updateSalarySettings(id,salarySettings));
    }
    // GET endpoint to retrieve the salary settings
    @GetMapping
    public ResponseEntity <List<SalarySettings>> getSalarySettings() {
        return ResponseEntity.ok(Collections.singletonList(salarySettingsService.getSalarySettings()));
    }
}
