package com.medecineWebApp.Finance_service.service;

import com.medecineWebApp.Finance_service.dto.PurchaseItemDTO;
import com.medecineWebApp.Finance_service.models.PurchaseItem;

import java.util.List;

public interface PurchaseItemService {
    List<PurchaseItemDTO> getItemsByPurchaseOrder(Long orderId);
    PurchaseItemDTO addItemToOrder(Long orderId, PurchaseItem item);
    PurchaseItemDTO updateItem(Long orderId, Long itemId, PurchaseItem updatedItem);
    void deleteItem(Long orderId, Long itemId);
}
