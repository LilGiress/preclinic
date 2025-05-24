package com.medecineWebApp.Finance_service.dto;

import com.medecineWebApp.Finance_service.models.PurchaseOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseItemDTO extends AuditableDTO {
    private Long id;
    private String itemName;
    private int quantity;
    private double pricePerUnit;
    private PurchaseOrderDTO purchaseOrder;
}
