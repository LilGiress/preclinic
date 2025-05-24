package com.medecineWebApp.Employees.controller;

import com.medecineWebApp.Employees.dto.DoctorDTO;
import com.medecineWebApp.Employees.dto.EmployeeDTO;
import com.medecineWebApp.Employees.models.doctors.Doctor;
import com.medecineWebApp.Employees.services.DoctorService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {
    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }
    @PostMapping("/create")
    public ResponseEntity<DoctorDTO> createDoctor(@RequestBody Doctor doctor) {
        return ResponseEntity.ok(doctorService.saveDoctor(doctor));
    }
    @PutMapping("/update")
    public ResponseEntity<DoctorDTO> updateDoctor(@RequestParam Long id, @RequestBody Doctor doctor) {
        return ResponseEntity.ok(doctorService.updateDoctor(id,doctor));
    }

    @GetMapping("/{doctorId}")
    public ResponseEntity<DoctorDTO> getDoctorById(@PathVariable Long doctorId) {
        return ResponseEntity.ok(doctorService.getDoctorById(doctorId));
    }
    @GetMapping("/search")
    public ResponseEntity<Page<DoctorDTO>> getAllDoctors(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(doctorService.getAllDoctors(name, page, size));

    }

    @DeleteMapping
    public void deleteDoctorById(@RequestParam Long id) {
        doctorService.deleteDoctor(id);
    }

    @GetMapping("/{doctorId}/assets")
    public DoctorDTO getDoctorWithAssets(@PathVariable Long doctorId) {
        return doctorService.getDoctorWithAssets(doctorId);
    }
}
