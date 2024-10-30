package com.medecineWebApp.Configuration.repository.service;

import com.medecineWebApp.Configuration.models.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
}
