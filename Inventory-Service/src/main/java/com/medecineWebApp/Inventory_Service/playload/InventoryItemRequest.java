package com.medecineWebApp.Inventory_Service.playload;

import com.medecineWebApp.Inventory_Service.models.InventoryCategory;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InventoryItemRequest {
    private String name;
    private int expectedQuantity;
    private String categoryName;
}
