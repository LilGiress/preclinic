package com.medecineWebApp.Finance_service.repository;

import com.medecineWebApp.Finance_service.models.PurchaseItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseItemRepository extends JpaRepository<PurchaseItem, Long> {
    List<PurchaseItem> findByPurchaseOrderId(Long orderId);
}
