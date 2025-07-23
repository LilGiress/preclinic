package com.medecineWebApp.Inventory_Service.mapper;

import com.medecineWebApp.Inventory_Service.dto.InventoryRecordDTO;
import com.medecineWebApp.Inventory_Service.models.InventoryRecord;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface InventoryRecordMapper {
    InventoryRecordDTO mapToDto(InventoryRecord model);
    @InheritInverseConfiguration
    InventoryRecord toEntity(InventoryRecordDTO dto);

}
