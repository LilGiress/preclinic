package com.medecineWebApp.Finance_service.mapper;

import com.medecineWebApp.Finance_service.dto.PurchaseOrderDTO;
import com.medecineWebApp.Finance_service.models.PurchaseOrder;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PurchaseOrderMapper {
    @InheritInverseConfiguration
    PurchaseOrder toDTO(PurchaseOrderDTO dto);
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    PurchaseOrderDTO fromDTO(PurchaseOrder dto);
}
