package com.medecineWebApp.Inventory_Service.repository;

import com.medecineWebApp.Inventory_Service.models.InventoryRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface InventoryRecordRepository extends JpaRepository<InventoryRecord, Long> {
    List<InventoryRecord> findByCheckedAtBetween(LocalDateTime start, LocalDateTime end);
}
