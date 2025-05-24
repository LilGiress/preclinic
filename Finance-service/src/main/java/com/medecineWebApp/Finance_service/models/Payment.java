package com.medecineWebApp.Finance_service.models;

import com.medecineWebApp.Finance_service.enums.PaymentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDate;
@Entity
@Table(name = "payment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Payment extends Auditable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String invoiceNumber;
    private int userId;
    private LocalDate paidDate;
    private Double amount;
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;
    @ManyToOne
    private Invoice invoice;
}
