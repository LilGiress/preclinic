package com.medecineWebApp.patients.service.impl;

import com.medecineWebApp.patients.dto.FavoriteDTO;
import com.medecineWebApp.patients.exception.FavoriteNotFoundException;
import com.medecineWebApp.patients.mapper.FavoriteMapper;
import com.medecineWebApp.patients.models.Favorite;
import com.medecineWebApp.patients.repository.FavoriteRepository;
import com.medecineWebApp.patients.service.FavoriteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteServiceImpl implements FavoriteService {
    private final FavoriteRepository favoriteRepository;

    private final FavoriteMapper favoriteMapper;

    public FavoriteServiceImpl(FavoriteRepository favoriteRepository,   FavoriteMapper favoriteMapper) {
        this.favoriteRepository = favoriteRepository;
        this.favoriteMapper = favoriteMapper;
    }

    @Override
    public FavoriteDTO addFavorite(Long patientId, Long doctorId) {

        if (patientId!=null && doctorId!=null) {
            Favorite favorite=new Favorite();
            favorite.setDoctorId(doctorId);
            favorite.setPatientId(patientId);
            return favoriteMapper.toFavoriteDTO(favoriteRepository.save(favorite));

        }else throw new FavoriteNotFoundException("Patient or Doctor not found");


    }

    @Override
    public List<FavoriteDTO> getFavoritesByPatient(Long patientId) {
        return favoriteRepository.findByPatientId(patientId).stream()
                .map(favoriteMapper::toFavoriteDTO).toList();

    }

    @Override
    public List<FavoriteDTO> getFavoritesByDoctor(Long doctorId) {
        return favoriteRepository.findByDoctorId(doctorId)
                .stream().map(favoriteMapper::toFavoriteDTO).toList();

    }

    @Override
    public void removeFavoriteDoctor(Long patientId, Long doctorId) {

        // Check if the relationship exists
        if (!favoriteRepository.existsByPatientIdAndDoctorId(patientId, doctorId)) {
            throw new RuntimeException("Favorite relationship not found");
        }
        // Remove the favorite relationship
        favoriteRepository.deleteByPatientIdAndDoctorId(patientId, doctorId);
    }
}
