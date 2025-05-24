package com.medecineWebApp.Inventory_Service.mapper;

import com.medecineWebApp.Inventory_Service.dto.PrescriptionItemDTO;
import com.medecineWebApp.Inventory_Service.models.PrescriptionItem;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PrescriptionItemMapper {
    @Mapping(source = "createdBy", target = "createdBy")
    @Mapping(source = "createdDate", target = "createdDate")
    @Mapping(source = "lastModifiedBy", target = "lastModifiedBy")
    @Mapping(source = "lastModifiedDate", target = "lastModifiedDate")
    PrescriptionItemDTO toPrescriptionItemDTO(PrescriptionItem prescriptionItem);
    @InheritInverseConfiguration
    PrescriptionItem toPrescriptionItem(PrescriptionItemDTO prescriptionItemDTO);
}
