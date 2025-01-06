package com.medecineWebApp.Accounts.models;

import com.medecineWebApp.Accounts.enums.InvoiceStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
@Entity
@Table(name = "invoices")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Invoices extends Auditable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String invoiceNumber;
    private String email;
    private Long patientId;
    private Long departmentId;
    private Long taxId;
    private LocalDate invoiceDate;
    private String billingAddress;
    private String otherInformation;
    private LocalDate invoicedatedue;
    @Enumerated(EnumType.STRING)
    private InvoiceStatus invoiceStatus;


}
