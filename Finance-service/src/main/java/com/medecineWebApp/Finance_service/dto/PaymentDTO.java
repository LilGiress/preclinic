package com.medecineWebApp.Finance_service.dto;

import com.medecineWebApp.Finance_service.enums.PaymentType;
import com.medecineWebApp.Finance_service.models.Invoice;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO extends AuditableDTO{
    private int id;
    @Column(unique = true)
    private String invoiceNumber;
    private int patientId;
    private LocalDate paidDate;
    private Double amount;
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;
    private InvoiceDTO invoice;
}
