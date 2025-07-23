package com.medecineWebApp.Inventory_Service.mapper;

import com.medecineWebApp.Inventory_Service.dto.PrescriptionItemDTO;
import com.medecineWebApp.Inventory_Service.models.PrescriptionItem;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PrescriptionItemMapper {
    PrescriptionItemDTO toPrescriptionItemDTO(PrescriptionItem prescriptionItem);
    @InheritInverseConfiguration
    PrescriptionItem toPrescriptionItem(PrescriptionItemDTO prescriptionItemDTO);
}
