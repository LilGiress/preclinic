package com.medecineWebApp.Configuration.controller;

import com.medecineWebApp.Configuration.dto.SalarySettingsDTO;
import com.medecineWebApp.Configuration.models.setting.SalarySettings;
import com.medecineWebApp.Configuration.service.SalarySettingsService;
import org.springframework.data.domain.Page;
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
    public ResponseEntity <SalarySettingsDTO> saveSalarySettings(SalarySettings salarySettings) {
        return ResponseEntity.ok(salarySettingsService.createSalarySettings(salarySettings));
    }
    //  PUT endpoint to  update the salary settings
    @PutMapping
    public ResponseEntity <SalarySettingsDTO> updateSalarySettings(@RequestParam Long id, @RequestBody SalarySettings salarySettings) {
        return ResponseEntity.ok(salarySettingsService.updateSalarySettings(id,salarySettings));
    }
    // GET endpoint to retrieve the salary settings
    @GetMapping
    public ResponseEntity <Page<SalarySettingsDTO>> getSalarySettings(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(salarySettingsService.getSalarySettings(page, size));
    }
}
