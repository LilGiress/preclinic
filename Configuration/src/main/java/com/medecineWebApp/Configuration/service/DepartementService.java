package com.medecineWebApp.Configuration.service;

import com.medecineWebApp.Configuration.dto.DepartementDTO;
import com.medecineWebApp.Configuration.models.Departement;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface DepartementService {
    DepartementDTO createDepartement(Departement department);
    DepartementDTO updateDepartement(Long id, Departement department);
    Optional<DepartementDTO> getDepartementById(Long id);
    List<DepartementDTO> getAllDepartements();
    void deleteDepartement(Long id);
}