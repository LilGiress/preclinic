package com.medecineWebApp.Configuration.controller;

import com.medecineWebApp.Configuration.dto.HolidayDTO;
import com.medecineWebApp.Configuration.models.Holiday;
import com.medecineWebApp.Configuration.service.HolidayService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/holidays")
public class HolidayController {
    private final HolidayService holidayService;

    public HolidayController(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    // Ajouter un jour férié
    @PostMapping
    public ResponseEntity<HolidayDTO> addHoliday(@RequestBody Holiday holiday) {
        return  ResponseEntity.ok(holidayService.addHoliday(holiday));
    }

    // Récupérer tous les jours fériés
    @GetMapping
    public List<HolidayDTO> getAllHolidays() {
        return holidayService.getAllHolidays();
    }

    // Récupérer un jour férié par ID
    @GetMapping("/{id}")
    public ResponseEntity<HolidayDTO> getHolidayById(@PathVariable Long id) {
        return  ResponseEntity.ok( holidayService.getHolidayById(id));
    }

    // Récupérer un jour férié par date
    @GetMapping("/date/{date}")
    public ResponseEntity<List<HolidayDTO>> getHolidaysByDate(@PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date);
        return  ResponseEntity.ok(holidayService.getHolidaysByDate(localDate));
    }

    // Mettre à jour un jour férié
    @PutMapping("/{id}")
    public ResponseEntity<HolidayDTO> updateHoliday(@PathVariable Long id, @RequestBody Holiday holiday) {
        return ResponseEntity.ok(holidayService.updateHoliday(id, holiday));
    }

    // Supprimer un jour férié
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHoliday(@PathVariable Long id) {
        holidayService.deleteHoliday(id);
        return ResponseEntity.noContent().build();
    }

    // Vérifier si une date est un jour férié
    @GetMapping("/isHoliday/{date}")
    public ResponseEntity<Boolean> isHoliday(@PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date);
        boolean isHoliday = holidayService.isHoliday(localDate);
        return ResponseEntity.ok(isHoliday);
    }

}
