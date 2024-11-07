package com.medecineWebApp.Employees.controller;


import com.medecineWebApp.Employees.dto.DoctorScheduleDTO;
import com.medecineWebApp.Employees.enums.ScheduleStatus;
import com.medecineWebApp.Employees.models.doctors.DoctorSchedule;
import com.medecineWebApp.Employees.services.DoctorScheduleService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/schedules")
public class DoctorScheduleController {
    private final DoctorScheduleService doctorScheduleService;

    public DoctorScheduleController(DoctorScheduleService doctorScheduleService) {
        this.doctorScheduleService = doctorScheduleService;
    }

    @GetMapping
    public ResponseEntity<Page<DoctorScheduleDTO>> getAllSchedules(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(doctorScheduleService.getAllDoctorSchedule(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorScheduleDTO> getScheduleById(@PathVariable Long id) {
        return doctorScheduleService.getDoctorScheduleById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DoctorScheduleDTO> createSchedule(@RequestBody DoctorSchedule schedule) {
        return ResponseEntity.ok(doctorScheduleService.saveDoctorSchedule(schedule));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorScheduleDTO> updateSchedule(@PathVariable Long id, @RequestBody DoctorSchedule scheduleDetails) {
        try {
            DoctorScheduleDTO updatedSchedule = doctorScheduleService.updateDoctorSchedule(id, scheduleDetails);
            return ResponseEntity.ok(updatedSchedule);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        doctorScheduleService.deleteDoctorScheduleById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public Page<DoctorScheduleDTO> findSchedulesByDoctorAndDate(
            @RequestParam Long doctorId,
            @RequestParam LocalDate date,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
            ) {
        return doctorScheduleService.findSchedulesByDoctorAndDate(doctorId, date,page, size);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<DoctorScheduleDTO> updateScheduleStatus(@PathVariable Long id, @RequestParam ScheduleStatus status) {
        try {
            DoctorScheduleDTO updatedSchedule = doctorScheduleService.updateScheduleStatus(id, status);
            return ResponseEntity.ok(updatedSchedule);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/available-days")
    public ResponseEntity<Set<DayOfWeek>> getAvailableDays(@PathVariable Long id) {
        try {
            EnumSet<DayOfWeek> availableDays = doctorScheduleService.getAvailableDays(id);
            return ResponseEntity.ok(availableDays);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/available-days")
    public ResponseEntity<DoctorScheduleDTO> setAvailableDays(@PathVariable Long id, @RequestBody Set<DayOfWeek> days) {
        try {
            DoctorScheduleDTO updatedSchedule = doctorScheduleService.setAvailableDays(id, EnumSet.copyOf(days));
            return ResponseEntity.ok(updatedSchedule);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
