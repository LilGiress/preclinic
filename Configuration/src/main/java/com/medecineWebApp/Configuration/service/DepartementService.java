package com.medecineWebApp.Configuration.service;

import com.medecineWebApp.Configuration.models.Departement;

import java.util.List;
import java.util.Optional;

public interface DepartementService {
    Departement createDepartement(Departement department);
    Departement updateDepartement(Long id, Departement department);
    Optional<Departement> getDepartementById(Long id);
    List<Departement> getAllDepartements();
    void deleteDepartement(Long id);
}
