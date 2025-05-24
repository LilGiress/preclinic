package com.medecineWebApp.Employees.repository;

import com.medecineWebApp.Employees.models.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
}
