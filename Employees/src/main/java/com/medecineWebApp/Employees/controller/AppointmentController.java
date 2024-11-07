package com.medecineWebApp.Employees.controller;


import com.medecineWebApp.Employees.enums.AppointmentStatus;
import com.medecineWebApp.Employees.models.doctors.Appointment;
import com.medecineWebApp.Employees.services.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable Long id) {
        return appointmentService.getAppointmentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Appointment createAppointment(@RequestBody Appointment appointment) {
        return appointmentService.createAppointment(appointment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable Long id, @RequestBody Appointment appointmentDetails) {
        try {
            Appointment updatedAppointment = appointmentService.updateAppointment(id, appointmentDetails);
            return ResponseEntity.ok(updatedAppointment);
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
    public ResponseEntity<List<Appointment>> findAppointmentsByDoctorAndDate(@RequestParam Long doctorId, @RequestParam LocalDate date) {
        return ResponseEntity.ok(appointmentService.findAppointmentsByDoctorAndDate(doctorId, date));
    }

    @GetMapping("/search/patient")
    public ResponseEntity<List<Appointment>> findAppointmentsByPatientAndDate(@RequestParam Long patientId, @RequestParam LocalDate date) {
        return ResponseEntity.ok(appointmentService.findAppointmentsByPatientAndDate(patientId, date));
    }

    @GetMapping("/search/date")
    public ResponseEntity<List<Appointment>> findAppointmentsByDate(@RequestParam LocalDate date) {
        return ResponseEntity.ok(appointmentService.findAppointmentsByDate(date));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Appointment> updateAppointmentStatus(@PathVariable Long id, @RequestParam AppointmentStatus status) {
        try {
            Appointment updatedAppointment = appointmentService.updateAppointmentStatus(id, status);
            return ResponseEntity.ok(updatedAppointment);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
