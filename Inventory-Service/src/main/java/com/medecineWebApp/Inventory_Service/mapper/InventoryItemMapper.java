package com.medecineWebApp.Inventory_Service.mapper;


import com.medecineWebApp.Inventory_Service.dto.InventoryItemDTO;
import com.medecineWebApp.Inventory_Service.models.InventoryItem;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface InventoryItemMapper {
    @Mapping(source = "createdBy", target = "createdBy")
    @Mapping(source = "createdDate", target = "createdDate")
    @Mapping(source = "lastModifiedBy", target = "lastModifiedBy")
    @Mapping(source = "lastModifiedDate", target = "lastModifiedDate")
    InventoryItemDTO toDTO(InventoryItem item);
    @InheritInverseConfiguration
    InventoryItem fromDTO(InventoryItemDTO dto);
}
