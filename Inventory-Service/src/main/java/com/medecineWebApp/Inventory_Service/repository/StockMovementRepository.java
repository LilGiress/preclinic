package com.medecineWebApp.Inventory_Service.repository;

import com.medecineWebApp.Inventory_Service.models.StockMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockMovementRepository extends JpaRepository<StockMovement, Long> {
    List<StockMovement> findAllBySupplierId(Long supplierId);
}
