package com.medecineWebApp.Inventory_Service.mapper;

import com.medecineWebApp.Inventory_Service.dto.InventoryCategoryDTO;
import com.medecineWebApp.Inventory_Service.models.InventoryCategory;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface InventoryCategoryMapper {
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    InventoryCategoryDTO entityToDto(InventoryCategory inventoryCategory);
    @InheritInverseConfiguration
    InventoryCategory dtoToEntity(InventoryCategoryDTO inventoryCategoryDTO);
}
