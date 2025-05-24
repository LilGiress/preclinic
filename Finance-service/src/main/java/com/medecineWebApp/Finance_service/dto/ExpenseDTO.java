package com.medecineWebApp.Finance_service.dto;

import com.medecineWebApp.Finance_service.enums.ExpenseCategory;
import com.medecineWebApp.Finance_service.enums.PaymentType;
import com.medecineWebApp.Finance_service.enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.File;
import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseDTO extends AuditableDTO{
    private Long id;
    private String itemName;
    private String purchaseFrom;
    private LocalDate purchaseDate;
    private Double amount;
    private Long userId;
    private File attachment;
    private PaymentType paidBy;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    private ExpenseCategory category;

    private Long inventoryItemId; // Dépense liée à un produit de l'inventaire

   private Long assetId; // Dépense liée à un actif
}
