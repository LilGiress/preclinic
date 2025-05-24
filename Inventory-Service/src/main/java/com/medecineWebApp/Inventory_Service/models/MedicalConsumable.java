package com.medecineWebApp.Inventory_Service.models;

import com.medecineWebApp.Inventory_Service.events.UnitType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDate;
@Entity
@Table(name = "medical_consumable")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class MedicalConsumable extends Auditable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String category; // Médicament, seringue, gants, etc.
    @Enumerated(EnumType.STRING)
    private UnitType unit; // ml, mg, unité
    private String manufacturer;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    private LocalDate expirationDate;
    private boolean requiresPrescription; // Nécessite une ordonnance ?
}
