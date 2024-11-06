package com.medecineWebApp.Configuration.dto;

import com.medecineWebApp.Configuration.enums.ExpenseCategory;
import com.medecineWebApp.Configuration.enums.PaymentType;
import com.medecineWebApp.Configuration.enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.File;
import java.time.LocalDate;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseDTO {
    private Long id;
    private String itemName;
    private String purchaseFrom;
    private LocalDate purchaseDate;
    private Double amount;
    private Long userId;
    private File attachment;
    private PaymentType paidBy;
    private Status status;
    private ExpenseCategory category;
}
