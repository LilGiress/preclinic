package com.medecineWebApp.Configuration.repository.service;

import com.medecineWebApp.Configuration.models.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Services, Long> {
}
