package com.medecineWebApp.Inventory_Service.repository;

import com.medecineWebApp.Inventory_Service.models.MedicalConsumable;
import com.medecineWebApp.Inventory_Service.models.Stock;
import io.micrometer.core.instrument.config.validate.Validated;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
   Stock findByConsumable(MedicalConsumable consumable);
}
