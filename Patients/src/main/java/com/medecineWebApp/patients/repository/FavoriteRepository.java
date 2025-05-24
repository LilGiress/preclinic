package com.medecineWebApp.patients.repository;

import com.medecineWebApp.patients.models.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    List<Favorite> findByPatientId(Long patientId);
    List<Favorite> findByDoctorId(Long doctorId);
    void deleteByPatientIdAndDoctorId(Long patientId, Long doctorId);
    boolean existsByPatientIdAndDoctorId (Long patientId, Long doctorId);
}
