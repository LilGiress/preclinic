package com.medecineWebApp.Inventory_Service.controller;

import com.medecineWebApp.Inventory_Service.dto.StockDTO;
import com.medecineWebApp.Inventory_Service.service.StockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock")
public class StockController {
    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }
    @GetMapping
    public ResponseEntity  <List<StockDTO>> getAllStock() {
        return ResponseEntity.ok(stockService.getAllStock());
    }

    @PostMapping("/add/{id}")
    public void addStock(@PathVariable Long id, @RequestParam int quantity) {
        stockService.addStock(id, quantity);
    }

    @PostMapping("/use/{id}")
    public void useStock(@PathVariable Long id, @RequestParam int quantity) {
        stockService.useStock(id, quantity);
    }
}
