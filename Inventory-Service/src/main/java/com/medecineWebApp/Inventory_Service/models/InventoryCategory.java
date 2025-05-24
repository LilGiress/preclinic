package com.medecineWebApp.Inventory_Service.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "inventory_category")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class InventoryCategory extends Auditable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name; // Ex: "Médicaments", "Fournitures", "Équipement médical"
    @OneToMany(mappedBy = "category")
    private List<InventoryItem> inventoryItems; // Liste des items liés à cette catégorie

    public InventoryCategory(String categoryName) {
    }
}
