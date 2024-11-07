package com.medecineWebApp.patients.repository;

import com.medecineWebApp.patients.enums.TreatmentStatus;
import com.medecineWebApp.patients.models.Treatment;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface TreatmentRepository extends JpaRepository<Treatment, Long>, JpaSpecificationExecutor<Treatment> {


}
