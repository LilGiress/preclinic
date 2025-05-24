package com.medecineWebApp.Inventory_Service.playload;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InventoryRequest {
    private Long itemId;
    private int countedQuantity;
    private Long checkedBy;
}
