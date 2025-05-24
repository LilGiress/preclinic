package com.medecineWebApp.Inventory_Service.service;

import com.medecineWebApp.Inventory_Service.models.Stock;
import com.medecineWebApp.Inventory_Service.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockAlertService {

    private final StockRepository stockRepository;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public StockAlertService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Scheduled(cron = "0 0 3 * * ?") // Vérifie chaque jour à 3h du matin
    public void checkStockLevels() {
        List<Stock> stocks = stockRepository.findAll();

        for (Stock stock : stocks) {
            if (stock.getQuantity() < stock.getMinThreshold()) {
                String alertMessage = "⚠️ Stock faible: " + stock.getConsumable().getName() + " (" + stock.getQuantity() + " restants)";
                kafkaTemplate.send("low-stock-alerts", alertMessage);
            }
        }
    }
}
