package com.medecineWebApp.patients.controller;

import com.medecineWebApp.patients.dto.FavoriteDTO;
import com.medecineWebApp.patients.service.FavoriteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {
    private final FavoriteService favoriteService;

    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    // Remove a doctor from a patient's favorites
    @DeleteMapping
    public void removeFavorite(@RequestParam Long patientId, @RequestParam Long doctorId) {
        favoriteService.removeFavoriteDoctor(patientId, doctorId);
    }
    @PostMapping
    public ResponseEntity<FavoriteDTO> addFavorite(@RequestParam Long patientId, @RequestParam Long doctorId) {
        return ResponseEntity.ok(favoriteService.addFavorite(patientId, doctorId));
    }
    @GetMapping("/by-patient/{patientId}")
    public ResponseEntity<List<FavoriteDTO>> getFavoritesByPatient(@PathVariable Long patientId) {
        return ResponseEntity.ok(favoriteService.getFavoritesByPatient(patientId));
    }
    @GetMapping("/by-doctor/{doctorId}")
    public ResponseEntity<List<FavoriteDTO>> getFavoritesByDoctor(@PathVariable Long doctorId) {
        return ResponseEntity.ok(favoriteService.getFavoritesByDoctor(doctorId));
    }


}
