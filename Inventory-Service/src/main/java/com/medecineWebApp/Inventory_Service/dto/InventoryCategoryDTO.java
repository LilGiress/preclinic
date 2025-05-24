package com.medecineWebApp.Inventory_Service.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InventoryCategoryDTO extends AuditableDTO{
    private Long id;
    private String name; // Ex: "Médicaments", "Fournitures", "Équipement médical"
    private List<InventoryItemDTO> inventoryItems;
}
