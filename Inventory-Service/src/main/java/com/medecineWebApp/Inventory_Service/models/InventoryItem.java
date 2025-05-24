package com.medecineWebApp.Inventory_Service.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "inventory_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class InventoryItem extends Auditable implements Serializable {
    //élément d'inventaire
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String itemName;
    private int quantity; // Quantité en stock
    private Long supplier; // Fournisseur
    private LocalDate expiryDate; // Date d'expiration
    @ManyToOne
    @JoinColumn(name = "category_id")
    private InventoryCategory category; // Référence à la catégorie de l'article
    private int expectedQuantity;
    private int actualQuantity;

}
