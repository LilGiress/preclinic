package com.medecineWebApp.Finance_service.service.impl;

import com.medecineWebApp.Finance_service.kafka.RestockEvent;
import com.medecineWebApp.Finance_service.kafka.RestockProducer;
import com.medecineWebApp.Finance_service.models.PurchaseOrder;
import com.medecineWebApp.Finance_service.repository.PurchaseOrderRepository;
import com.medecineWebApp.Finance_service.service.RestockService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestockServiceImpl implements RestockService {
    private final PurchaseOrderRepository purchaseOrderRepository;
    private final RestockProducer restockProducer;

    public RestockServiceImpl(PurchaseOrderRepository purchaseOrderRepository, RestockProducer restockProducer) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.restockProducer = restockProducer;
    }

    @Override
    public void restockFromPurchaseOrder(Long purchaseOrderId) {
        PurchaseOrder order = purchaseOrderRepository.findById(purchaseOrderId)
                .orElseThrow(() -> new RuntimeException("Commande introuvable"));

        List<RestockEvent.RestockItem> items = order.getPurchaseItems().stream()
                .map(item -> new RestockEvent.RestockItem(item.getItemName(), item.getQuantity()))
                .toList();

        RestockEvent event = new RestockEvent(purchaseOrderId, items);
        restockProducer.sendRestockEvent(event);
    }
}
