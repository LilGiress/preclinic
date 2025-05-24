package com.medecineWebApp.Finance_service.controller;

import com.medecineWebApp.Finance_service.dto.PurchaseItemDTO;
import com.medecineWebApp.Finance_service.models.PurchaseItem;
import com.medecineWebApp.Finance_service.service.PurchaseItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchase-orders/{orderId}/items")
public class PurchaseItemController {

    private final PurchaseItemService purchaseItemService;

    public PurchaseItemController( PurchaseItemService purchaseItemService) {
        this.purchaseItemService = purchaseItemService;
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<List<PurchaseItemDTO>> getItemsByOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(purchaseItemService.getItemsByPurchaseOrder(orderId));
    }

    @PostMapping
    public ResponseEntity<PurchaseItemDTO> addItemToOrder(@PathVariable Long orderId, @RequestBody PurchaseItem item) {
        return  ResponseEntity.ok(purchaseItemService.addItemToOrder(orderId, item));
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<PurchaseItemDTO> updateItem(@PathVariable Long orderId,
                                                   @PathVariable Long itemId,
                                                   @RequestBody PurchaseItem updatedItem) {
        return ResponseEntity.ok(purchaseItemService.updateItem(orderId, itemId, updatedItem));
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long orderId, @PathVariable Long itemId) {
        purchaseItemService.deleteItem(orderId, itemId);
        return ResponseEntity.noContent().build();
    }

}
