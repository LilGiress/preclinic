package com.medecineWebApp.Finance_service.service.impl;

import com.medecineWebApp.Finance_service.dto.PurchaseItemDTO;
import com.medecineWebApp.Finance_service.mapper.PurchaseItemMapper;
import com.medecineWebApp.Finance_service.models.PurchaseItem;
import com.medecineWebApp.Finance_service.models.PurchaseOrder;
import com.medecineWebApp.Finance_service.repository.PurchaseItemRepository;
import com.medecineWebApp.Finance_service.repository.PurchaseOrderRepository;
import com.medecineWebApp.Finance_service.service.PurchaseItemService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseItemServiceImpl implements PurchaseItemService {
    private final PurchaseOrderRepository purchaseOrderRepository;
    private final PurchaseItemRepository purchaseItemRepository;
    private final PurchaseItemMapper purchaseItemMapper;

    public PurchaseItemServiceImpl(PurchaseOrderRepository purchaseOrderRepository, PurchaseItemRepository purchaseItemRepository, PurchaseItemMapper purchaseItemMapper) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.purchaseItemRepository = purchaseItemRepository;
        this.purchaseItemMapper = purchaseItemMapper;
    }

    @Override
    public List<PurchaseItemDTO> getItemsByPurchaseOrder(Long orderId) {
        return purchaseItemRepository.findByPurchaseOrderId(orderId).stream().map(purchaseItemMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PurchaseItemDTO addItemToOrder(Long orderId, PurchaseItem item) {
        PurchaseOrder order = getOrderOrThrow(orderId);
        item.setPurchaseOrder(order);
        updateTotalAmount(order);
        return  purchaseItemMapper.toDTO(purchaseItemRepository.save(item));
    }

    @Override
   // @Transactional
    public PurchaseItemDTO updateItem(Long orderId, Long itemId, PurchaseItem updatedItem) {
        PurchaseOrder order = getOrderOrThrow(orderId);
        PurchaseItem existingItem = purchaseItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Article non trouvé"));

        existingItem.setItemName(updatedItem.getItemName());
        existingItem.setPricePerUnit(updatedItem.getPricePerUnit());
        existingItem.setQuantity(updatedItem.getQuantity());
        updateTotalAmount(order);
        return purchaseItemMapper.toDTO(purchaseItemRepository.save(existingItem)) ;
    }

    @Override
    @Transactional
    public void deleteItem(Long orderId, Long itemId) {
        PurchaseOrder order = getOrderOrThrow(orderId);
        purchaseItemRepository.deleteById(itemId);
        updateTotalAmount(order);
    }

    private void updateTotalAmount(PurchaseOrder order) {
        List<PurchaseItem> items = purchaseItemRepository.findByPurchaseOrderId(order.getId());
        double total = items.stream()
                .mapToDouble(item -> item.getPricePerUnit() * item.getQuantity())
                .sum();
        order.setTotalAmount(total);
        purchaseOrderRepository.save(order);
    }

    private PurchaseOrder getOrderOrThrow(Long orderId) {
        return purchaseOrderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Bon de commande introuvable"));
    }
}
