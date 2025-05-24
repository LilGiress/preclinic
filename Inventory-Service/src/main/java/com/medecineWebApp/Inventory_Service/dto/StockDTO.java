package com.medecineWebApp.Inventory_Service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StockDTO extends AuditableDTO{
    private Long id;
    private MedicalConsumableDTO consumable;
    private int quantity;
    private int minThreshold;
}
