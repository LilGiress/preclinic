package com.medecineWebApp.patients.service;

import com.medecineWebApp.patients.dto.FavoriteDTO;

import java.util.List;

public interface FavoriteService {
FavoriteDTO addFavorite(Long patientId, Long doctorId);
    List<FavoriteDTO> getFavoritesByPatient(Long patientId);
    List<FavoriteDTO> getFavoritesByDoctor(Long doctorId);
    void removeFavoriteDoctor(Long patientId, Long doctorId);
}
