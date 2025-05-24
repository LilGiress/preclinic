package com.medecineWebApp.Inventory_Service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PurchaseItemDTO {
    private String itemName;
    private int quantity;
}
