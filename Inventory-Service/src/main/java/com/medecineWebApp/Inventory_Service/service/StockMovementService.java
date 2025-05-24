package com.medecineWebApp.Inventory_Service.service;

import com.medecineWebApp.Inventory_Service.dto.StockMovementDTO;
import com.medecineWebApp.Inventory_Service.models.StockMovement;

import java.util.List;

public interface StockMovementService {
    StockMovementDTO getStockMovementById(Long id);
    List<StockMovementDTO> getStockMovementsBySupplierId(Long supplierId);
    StockMovementDTO addStockMovement(StockMovement stockMovement);
    StockMovementDTO updateStockMovement(Long stockMovementId,StockMovement stockMovement);
    void deleteStockMovement(StockMovement stockMovement);
}
