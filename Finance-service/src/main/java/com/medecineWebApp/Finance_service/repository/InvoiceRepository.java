package com.medecineWebApp.Finance_service.repository;

import com.medecineWebApp.Finance_service.models.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long>, JpaSpecificationExecutor<Invoice> {
    List<Invoice> findByUserId(Long patientId);
    Optional<Invoice> findTopByOrderByIdDesc();
    List<Invoice> findByUserIdOrderByIdDesc(Long userId);
}
