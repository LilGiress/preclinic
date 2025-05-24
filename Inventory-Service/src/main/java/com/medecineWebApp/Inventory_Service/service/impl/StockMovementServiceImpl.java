package com.medecineWebApp.Inventory_Service.service.impl;

import com.medecineWebApp.Inventory_Service.dto.StockMovementDTO;
import com.medecineWebApp.Inventory_Service.exception.StockMovementNotFoundException;
import com.medecineWebApp.Inventory_Service.mapper.StockMovementMapper;
import com.medecineWebApp.Inventory_Service.models.StockMovement;
import com.medecineWebApp.Inventory_Service.repository.StockMovementRepository;
import com.medecineWebApp.Inventory_Service.service.StockMovementService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockMovementServiceImpl implements StockMovementService {
    private final StockMovementMapper stockMovementMapper;
    private final StockMovementRepository stockMovementRepository;


    public StockMovementServiceImpl(StockMovementMapper stockMovementMapper, StockMovementRepository stockMovementRepository) {
        this.stockMovementMapper = stockMovementMapper;
        this.stockMovementRepository = stockMovementRepository;

    }

    @Override
    public StockMovementDTO getStockMovementById(Long id) {
        StockMovement stockMovement = stockMovementRepository.findById(id)
                .orElseThrow(() -> new StockMovementNotFoundException("StockMovement not found"));
        return stockMovementMapper.toStockMovementDTO(stockMovement);
    }

    @Override
    public List<StockMovementDTO> getStockMovementsBySupplierId(Long supplierId) {
        List<StockMovement> stockMovementList = stockMovementRepository.findAllBySupplierId(supplierId);
        return stockMovementList.stream()
                .map(stockMovementMapper::toStockMovementDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StockMovementDTO addStockMovement(StockMovement stockMovement) {
        return stockMovementMapper.toStockMovementDTO(stockMovementRepository.save(stockMovement));
    }

    @Override
    public StockMovementDTO updateStockMovement(Long stockMovementId,StockMovement stockMovement) {
        StockMovement stockMovement1 = stockMovementRepository.findById(stockMovementId)
                .orElseThrow(() -> new StockMovementNotFoundException("StockMovement not found"));
        stockMovement1.setQuantity(stockMovement.getQuantity());
        stockMovement1.setMovementType(stockMovement.getMovementType());
        stockMovement1.setConsumable(stockMovement.getConsumable());
        stockMovement1.setPatientId(stockMovement.getPatientId());
        stockMovement1.setTimestamp(stockMovement.getTimestamp());

        return stockMovementMapper.toStockMovementDTO(stockMovementRepository.save(stockMovement1));
    }

    @Override
    public void deleteStockMovement(StockMovement stockMovement) {
        stockMovementRepository.delete(stockMovement);

    }
}
