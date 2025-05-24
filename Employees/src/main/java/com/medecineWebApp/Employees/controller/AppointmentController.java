package com.medecineWebApp.Employees.controller;


import com.medecineWebApp.Employees.dto.AppointmentDTO;
import com.medecineWebApp.Employees.enums.AppointmentStatus;
import com.medecineWebApp.Employees.models.Appointment;
import com.medecineWebApp.Employees.models.doctors.Doctor;
import com.medecineWebApp.Employees.services.AppointmentService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/search")
    public ResponseEntity<Page<AppointmentDTO>> getAllAppointments(
            @RequestParam Long patient,
            @RequestParam Doctor doctor,
            @RequestParam String date,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(appointmentService.getAllAppointments(patient,doctor,date,page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDTO> getAppointmentById(@PathVariable Long id) {
        return appointmentService.getAppointmentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public AppointmentDTO createAppointment(@RequestBody Appointment appointment) {
        return appointmentService.createAppointment(appointment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppointmentDTO> updateAppointment(@PathVariable Long id, @RequestBody Appointment appointmentDetails) {
        try {
            return ResponseEntity.ok(appointmentService.updateAppointment(id, appointmentDetails));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/doctor")
    public ResponseEntity<List<AppointmentDTO>> findAppointmentsByDoctorAndDate(@RequestParam Long doctorId, @RequestParam LocalDate date) {
        return ResponseEntity.ok(appointmentService.findAppointmentsByDoctorAndDate(doctorId, date));
    }

//    @GetMapping("/search/patient")
//    public ResponseEntity<List<AppointmentDTO>> findAppointmentsByPatientAndDate(@RequestParam Patient patientId, @RequestParam LocalDate date) {
//        return ResponseEntity.ok(appointmentService.findAppointmentsByPatientAndDate(patientId, date));
//    }

    @GetMapping("/search/date")
    public ResponseEntity<List<AppointmentDTO>> findAppointmentsByDate(@RequestParam LocalDate date) {
        return ResponseEntity.ok(appointmentService.findAppointmentsByDate(date));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<AppointmentDTO> updateAppointmentStatus(@PathVariable Long id, @RequestParam AppointmentStatus status) {
        try {
            return ResponseEntity.ok(appointmentService.updateAppointmentStatus(id, status));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/count-by-doctor/{doctorId}")
    public ResponseEntity<Long>  countAppointmentsByDoctor(@PathVariable Long doctorId) {
        return ResponseEntity.ok( appointmentService.countAppointmentsByDoctor(doctorId));
    }

    @GetMapping("/upcoming-by-doctor/{doctorId}")
    public ResponseEntity<List<AppointmentDTO>> getUpcomingAppointmentsByDoctor(@PathVariable Long doctorId) {
        return ResponseEntity.ok(appointmentService.getUpcomingAppointmentsByDoctor(doctorId));
    }

    @GetMapping("/today-by-doctor/{doctorId}")
    public ResponseEntity<List<AppointmentDTO>> getTodayAppointmentsByDoctor(@PathVariable Long doctorId) {
        return ResponseEntity.ok(appointmentService.getTodayAppointmentsByDoctor(doctorId));
    }
}
