package com.medecineWebApp.Finance_service.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.medecineWebApp.Finance_service.enums.InvoiceStatus;
import com.medecineWebApp.Finance_service.models.InvoiceItem;
import com.medecineWebApp.Finance_service.models.Payment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDTO extends AuditableDTO {
    private Long id;
    @Column(unique = true)
    private String invoiceNumber;
    @Column(name = "user_email", nullable = false)
    private String email;
    @Column(name = "user_id", nullable = false)
    private Long userId;
    @Column(name = "department_id", nullable = false)
    private Long departmentId;
    private Double tax;
    private Double total;
    private Double discount;
    private LocalDate dueDate;
    private LocalDateTime invoiceDate;
    @Column(name = "user_address", nullable = false)
    private String billingAddress;
    private String otherInformation;
    private LocalDateTime invoicedatedue;
    @Enumerated(EnumType.STRING)
    private InvoiceStatus invoiceStatus;

    private List<PaymentDTO> payments;

    @JsonManagedReference
    private List<InvoiceItemDTO> invoiceItems = new ArrayList<>();
}
