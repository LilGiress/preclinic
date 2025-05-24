package com.medecineWebApp.Inventory_Service.repository;

import com.medecineWebApp.Inventory_Service.models.InventoryCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryCategoryRepository extends JpaRepository<InventoryCategory, Long> {
    Optional<InventoryCategory> findByName(String name); // Recherche de catégorie par son nom
}
