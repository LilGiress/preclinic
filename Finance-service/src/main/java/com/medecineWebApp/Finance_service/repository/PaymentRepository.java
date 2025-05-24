package com.medecineWebApp.Finance_service.repository;

import com.medecineWebApp.Finance_service.dto.PaymentDTO;
import com.medecineWebApp.Finance_service.models.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    @Query("SELECT COALESCE(SUM(p.amount), 0) FROM Payment p WHERE p.invoice.id = :invoiceId")
    double sumByInvoiceId(@Param("invoiceId") Long invoiceId);

    Page<PaymentDTO> findAllByInvoiceIdAndUserId(Long invoiceId, Long userId, Pageable pageable);
}
