package com.medecineWebApp.Configuration.repository.service;

import com.medecineWebApp.Configuration.models.Departement;
import com.medecineWebApp.Configuration.models.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<Services, Long> {
    List<Services> findByDepartement(Departement department);
}
