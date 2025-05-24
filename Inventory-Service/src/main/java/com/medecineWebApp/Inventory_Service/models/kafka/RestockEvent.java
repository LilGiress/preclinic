package com.medecineWebApp.Inventory_Service.models.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestockEvent {
    private Long orderId;
    private List<RestockItem> items;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RestockItem {
        private String itemName;
        private int quantity;
    }
}
