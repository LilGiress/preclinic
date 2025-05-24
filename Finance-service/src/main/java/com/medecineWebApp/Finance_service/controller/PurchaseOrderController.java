package com.medecineWebApp.Finance_service.controller;

import com.medecineWebApp.Finance_service.dto.PurchaseOrderDTO;
import com.medecineWebApp.Finance_service.models.PurchaseOrder;
import com.medecineWebApp.Finance_service.service.PurchaseOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchase-orders")
public class PurchaseOrderController {

    private final PurchaseOrderService purchaseOrderService;

    public PurchaseOrderController(PurchaseOrderService purchaseOrderService) {
        this.purchaseOrderService = purchaseOrderService;
    }

    @PostMapping
    public ResponseEntity<PurchaseOrderDTO> create(@RequestBody PurchaseOrder purchaseOrder) {
        return ResponseEntity.ok(purchaseOrderService.create(purchaseOrder));
    }

//    @GetMapping
//    public ResponseEntity<List<PurchaseOrderDTO>> getAll() {
//        return ResponseEntity.ok(purchaseOrderService.getAll());
//    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseOrderDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(purchaseOrderService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PurchaseOrderDTO> update(@PathVariable Long id, @RequestBody PurchaseOrder updatedOrder) {
        return ResponseEntity.ok(purchaseOrderService.update(id, updatedOrder));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        purchaseOrderService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/validate")
    public ResponseEntity<PurchaseOrderDTO> validateOrder(@PathVariable Long id) {
        return ResponseEntity.ok(purchaseOrderService.validateOrder(id));
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<PurchaseOrderDTO> cancelOrder(@PathVariable Long id) {
        return ResponseEntity.ok(purchaseOrderService.cancelOrder(id));
    }

    @PostMapping("/{id}/receive")
    public ResponseEntity<PurchaseOrderDTO> receiveOrder(@PathVariable Long id) {
        return ResponseEntity.ok(purchaseOrderService.receiveOrder(id));
    }

//    @GetMapping("/export/pdf/{id}")
//    public ResponseEntity<byte[]> exportPdf(@PathVariable Long id) {
//        return purchaseOrderService.exportPdf(id);
//    }
//
//    @GetMapping("/history")
//    public ResponseEntity<List<PurchaseOrderDTO>> getHistory(
//            @RequestParam(required = false) String fromDate,
//            @RequestParam(required = false) String toDate,
//            @RequestParam(required = false) String status
//    ) {
//        return ResponseEntity.ok(purchaseOrderService.getHistory(fromDate, toDate, status));
//    }
}
