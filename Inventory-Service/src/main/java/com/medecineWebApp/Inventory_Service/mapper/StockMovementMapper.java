package com.medecineWebApp.Inventory_Service.mapper;

import com.medecineWebApp.Inventory_Service.dto.StockMovementDTO;
import com.medecineWebApp.Inventory_Service.models.StockMovement;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StockMovementMapper {
    StockMovementDTO toStockMovementDTO(StockMovement stockMovement);
    @InheritInverseConfiguration
    StockMovement toStockMovement(StockMovementDTO stockMovementDTO);

}
