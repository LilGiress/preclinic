package com.medecineWebApp.Finance_service.mapper;

import com.medecineWebApp.Finance_service.dto.PurchaseItemDTO;
import com.medecineWebApp.Finance_service.models.PurchaseItem;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PurchaseItemMapper {
    @InheritInverseConfiguration
    PurchaseItem fromDTO(PurchaseItemDTO dto);
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    PurchaseItemDTO toDTO(PurchaseItem item);
}
