package com.medecineWebApp.Configuration.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentsDTO {
    private int id;
    private String invoiceNumber;
    private int patientId;
    private LocalDate paidDate;
    private Double amount;
    private String paymentType;
}
