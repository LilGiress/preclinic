package com.medecineWebApp.Configuration.dto;

import com.medecineWebApp.Configuration.enums.InvoiceStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InvoicesDTO {
    private Long id;
    private String invoiceNumber;
    private String email;
    private Long patientId;
    private Long departmentId;
    private Long taxId;
    private LocalDate invoiceDate;
    private String billingAddress;
    private String otherInformation;
    private LocalDate invoicedatedue;
    private InvoiceStatus invoiceStatus;
}
