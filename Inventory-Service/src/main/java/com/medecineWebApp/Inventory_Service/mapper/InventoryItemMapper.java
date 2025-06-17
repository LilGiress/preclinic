package com.medecineWebApp.Inventory_Service.mapper;


import com.medecineWebApp.Inventory_Service.dto.InventoryItemDTO;
import com.medecineWebApp.Inventory_Service.models.InventoryItem;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface InventoryItemMapper {
    InventoryItemDTO toDTO(InventoryItem item);
    @InheritInverseConfiguration
    InventoryItem fromDTO(InventoryItemDTO dto);
}
