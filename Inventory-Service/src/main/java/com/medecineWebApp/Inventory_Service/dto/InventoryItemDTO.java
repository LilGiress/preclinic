package com.medecineWebApp.Inventory_Service.dto;

import com.medecineWebApp.Inventory_Service.models.InventoryCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryItemDTO extends AuditableDTO{
    private Long id;
    private String itemName;
    private int quantity; // Quantité en stock
    private Long supplier; // Fournisseur
    private LocalDate expiryDate; // Date d'expiration
    private InventoryCategoryDTO category; // Référence à la catégorie de l'article
    private int expectedQuantity;
    private int actualQuantity;
}
