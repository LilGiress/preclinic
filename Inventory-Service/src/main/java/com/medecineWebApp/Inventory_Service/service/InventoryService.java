package com.medecineWebApp.Inventory_Service.service;

import com.medecineWebApp.Inventory_Service.dto.InventoryItemDTO;
import com.medecineWebApp.Inventory_Service.dto.InventoryRecordDTO;
import com.medecineWebApp.Inventory_Service.dto.PurchaseItemDTO;
import com.medecineWebApp.Inventory_Service.playload.InventoryItemRequest;
import com.medecineWebApp.Inventory_Service.playload.InventoryRequest;

import java.time.LocalDateTime;
import java.util.List;

public interface InventoryService {
    InventoryRecordDTO recordInventory(InventoryRequest request);
    List<InventoryRecordDTO> getInventoryHistory(LocalDateTime start, LocalDateTime end);
    InventoryItemDTO addInventoryItem(InventoryItemRequest request);
    void restock(List<PurchaseItemDTO> items);
}
