package com.medecineWebApp.Inventory_Service.repository;


import com.medecineWebApp.Inventory_Service.models.InventoryCategory;
import com.medecineWebApp.Inventory_Service.models.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {
    List<InventoryItem> findByCategory(InventoryCategory category);
    Optional<InventoryItem> findByItemNameIgnoreCase(String itemName);
}
