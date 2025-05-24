package com.medecineWebApp.patients.repository;

import com.medecineWebApp.patients.models.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PatientRepository extends JpaRepository<Patient, Long>, JpaSpecificationExecutor<Patient> {
    @Query(value = "SELECT COUNT(DISTINCT p.id) FROM Patient p " +
            "JOIN Medical_Record mr ON p.id = mr.patient_id " +
            "WHERE mr.doctor_id = :doctorId",
            nativeQuery = true)
    Long countPatientsByDoctor(@Param("doctorId") Long doctorId);

    @Query(value = "SELECT COUNT(DISTINCT p.id) FROM Patient p " +
            "JOIN Medical_Record mr ON p.id = mr.patient_id " +
            "WHERE mr.doctor_id = :doctorId AND DATE(mr.date) = CURRENT_DATE",
            nativeQuery = true)
    Long countTodayPatientsByDoctor(@Param("doctorId") Long doctorId);
}
