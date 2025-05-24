package com.medecineWebApp.Finance_service.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.medecineWebApp.Finance_service.enums.InvoiceStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "invoices")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Invoice extends Auditable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String invoiceNumber;
    @Column(name = "user_email", nullable = false)
    private String email;
    @Column(name = "user_id", nullable = false)
    private Long userId;
    @Column(name = "department_id", nullable = false)
    private Long departmentId;
    private Long tax;
    private Double total;
    private Double discount;
    private LocalDateTime dueDate;
    private LocalDateTime invoiceDate;
    @Column(name = "user_address", nullable = false)
    private String billingAddress;
    private String otherInformation;
    private LocalDateTime invoicedatedue;
    @Enumerated(EnumType.STRING)
    private InvoiceStatus invoiceStatus;
    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
    private List<Payment> payments;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<InvoiceItem> invoiceItems = new ArrayList<>();

    // Add helper methods to manage bi-directional relationship
    public void addInvoiceItem(InvoiceItem item) {
        invoiceItems.add(item);
        item.setInvoice(this);
    }
    // Méthode pour retirer un item
    public void removeInvoiceItem(InvoiceItem item) {
        invoiceItems.remove(item);
        item.setInvoice(null);
    }

}
