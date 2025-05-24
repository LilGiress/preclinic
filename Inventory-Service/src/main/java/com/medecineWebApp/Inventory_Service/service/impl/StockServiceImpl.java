package com.medecineWebApp.Inventory_Service.service.impl;

import com.medecineWebApp.Inventory_Service.dto.StockDTO;
import com.medecineWebApp.Inventory_Service.exception.MedicalConsumableNotFoundException;
import com.medecineWebApp.Inventory_Service.exception.StockNotFoundException;
import com.medecineWebApp.Inventory_Service.mapper.StockMapper;
import com.medecineWebApp.Inventory_Service.models.MedicalConsumable;
import com.medecineWebApp.Inventory_Service.models.Stock;
import com.medecineWebApp.Inventory_Service.repository.MedicalConsumableRepository;
import com.medecineWebApp.Inventory_Service.repository.StockRepository;
import com.medecineWebApp.Inventory_Service.service.StockService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockServiceImpl implements StockService {
    private final StockRepository stockRepository;
    private final StockMapper stockMapper;
    private final MedicalConsumableRepository consumableRepository;

    public StockServiceImpl(StockRepository stockRepository, StockMapper stockMapper, MedicalConsumableRepository consumableRepository) {
        this.stockRepository = stockRepository;
        this.stockMapper = stockMapper;
        this.consumableRepository = consumableRepository;
    }

    @Override
    public List<StockDTO> getAllStock() {
        List<Stock> stocks = stockRepository.findAll();
        return stocks.stream().map(stockMapper::toStockDTO).collect(Collectors.toList());
    }

    @Override
    public void addStock(Long consumableId, int quantity) {
        MedicalConsumable consumable = consumableRepository.findById(consumableId)
                .orElseThrow(() -> new MedicalConsumableNotFoundException("Consommable non trouvé"));

        Stock stock = stockRepository.findByConsumable(consumable);

        if (stock == null) {
            stock = new Stock();
            stock.setConsumable(consumable);
            stock.setQuantity(quantity);
            stockRepository.save(stock);
        }else {
            stock.setQuantity(stock.getQuantity() - quantity);

            stockRepository.save(stock);
        }


    }

    @Override
    public void useStock(Long consumableId, int quantity) {
        MedicalConsumable consumable = consumableRepository.findById(consumableId)
                .orElseThrow(() -> new MedicalConsumableNotFoundException("Consommable non trouvé"));

        Stock stock = stockRepository.findByConsumable(consumable);
//                .orElseThrow(() -> new RuntimeException("Stock insuffisant"));


        if (stock != null && stock.getQuantity() < quantity) {
            throw new StockNotFoundException("Stock insuffisant !");
        }

        stock.setQuantity(stock.getQuantity() - quantity);
        stockRepository.save(stock);
    }
}
