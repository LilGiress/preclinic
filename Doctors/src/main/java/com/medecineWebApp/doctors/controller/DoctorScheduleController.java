package com.medecineWebApp.doctors.controller;

import com.medecineWebApp.doctors.emuns.ScheduleStatus;
import com.medecineWebApp.doctors.models.DoctorSchedule;
import com.medecineWebApp.doctors.service.DoctorScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/schedules")
public class DoctorScheduleController {
    private final DoctorScheduleService doctorScheduleService;

    public DoctorScheduleController(DoctorScheduleService doctorScheduleService) {
        this.doctorScheduleService = doctorScheduleService;
    }

    @GetMapping
    public ResponseEntity<List<DoctorSchedule>> getAllSchedules() {
        return ResponseEntity.ok(doctorScheduleService.getAllDoctorSchedule());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorSchedule> getScheduleById(@PathVariable Long id) {
        return doctorScheduleService.getDoctorScheduleById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DoctorSchedule> createSchedule(@RequestBody DoctorSchedule schedule) {
        return ResponseEntity.ok(doctorScheduleService.saveDoctorSchedule(schedule));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorSchedule> updateSchedule(@PathVariable Long id, @RequestBody DoctorSchedule scheduleDetails) {
        try {
            DoctorSchedule updatedSchedule = doctorScheduleService.updateDoctorSchedule(id, scheduleDetails);
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
    public List<DoctorSchedule> findSchedulesByDoctorAndDate(@RequestParam Long doctorId, @RequestParam LocalDate date) {
        return doctorScheduleService.findSchedulesByDoctorAndDate(doctorId, date);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<DoctorSchedule> updateScheduleStatus(@PathVariable Long id, @RequestParam ScheduleStatus status) {
        try {
            DoctorSchedule updatedSchedule = doctorScheduleService.updateScheduleStatus(id, status);
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
    public ResponseEntity<DoctorSchedule> setAvailableDays(@PathVariable Long id, @RequestBody Set<DayOfWeek> days) {
        try {
            DoctorSchedule updatedSchedule = doctorScheduleService.setAvailableDays(id, EnumSet.copyOf(days));
            return ResponseEntity.ok(updatedSchedule);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
