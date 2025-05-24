package com.medecineWebApp.Inventory_Service.service;

import com.medecineWebApp.Inventory_Service.dto.StockDTO;

import java.util.List;

public interface StockService {
    List<StockDTO> getAllStock();
    void addStock(Long consumableId, int quantity);
    void useStock(Long consumableId, int quantity);
}
