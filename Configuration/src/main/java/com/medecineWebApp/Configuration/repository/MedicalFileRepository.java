package com.medecineWebApp.Configuration.repository;

import com.medecineWebApp.Configuration.models.MedicalFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalFileRepository extends JpaRepository<MedicalFile, Long> {
}
