package com.medecineWebApp.Configuration.models.accounts;


import com.medecineWebApp.Configuration.enums.ExpenseCategory;
import com.medecineWebApp.Configuration.enums.PaymentType;
import com.medecineWebApp.Configuration.enums.Status;
import com.medecineWebApp.Configuration.models.Auditable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "expenses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Expense extends Auditable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
}
