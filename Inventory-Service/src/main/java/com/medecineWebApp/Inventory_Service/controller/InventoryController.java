package com.medecineWebApp.Inventory_Service.controller;

import com.medecineWebApp.Inventory_Service.dto.InventoryItemDTO;
import com.medecineWebApp.Inventory_Service.dto.InventoryRecordDTO;
import com.medecineWebApp.Inventory_Service.dto.PurchaseItemDTO;
import com.medecineWebApp.Inventory_Service.playload.InventoryItemRequest;
import com.medecineWebApp.Inventory_Service.playload.InventoryRequest;
import com.medecineWebApp.Inventory_Service.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping("/audit")
    public ResponseEntity<InventoryRecordDTO> recordInventory(@RequestBody InventoryRequest request) {
        return ResponseEntity.ok(inventoryService.recordInventory(request));
    }

    @GetMapping("/history")
    public ResponseEntity<List<InventoryRecordDTO>> getInventoryHistory(
            @RequestParam LocalDateTime start,
            @RequestParam LocalDateTime end) {
        return ResponseEntity.ok(inventoryService.getInventoryHistory(start, end));
    }

    @PostMapping("/save")
    public ResponseEntity<InventoryItemDTO> addInventoryItem(
            @RequestBody InventoryItemRequest request
            ) {
        return  ResponseEntity.ok(inventoryService.addInventoryItem(request));
    }

    @PostMapping("/restock")
    public ResponseEntity<Void> restockItems(@RequestBody List<PurchaseItemDTO> items) {
        inventoryService.restock(items);
        return ResponseEntity.ok().build();
    }
}
