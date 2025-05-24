package com.medecineWebApp.Inventory_Service.mapper;

import com.medecineWebApp.Inventory_Service.dto.InventoryRecordDTO;
import com.medecineWebApp.Inventory_Service.models.InventoryRecord;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InventoryRecordMapper {
    @Mapping(source = "createdBy", target = "createdBy")
    @Mapping(source = "createdDate", target = "createdDate")
    @Mapping(source = "lastModifiedBy", target = "lastModifiedBy")
    @Mapping(source = "lastModifiedDate", target = "lastModifiedDate")
    InventoryRecordDTO mapToDto(InventoryRecord model);
    @InheritInverseConfiguration
    InventoryRecord toEntity(InventoryRecordDTO dto);

}
