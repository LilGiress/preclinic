package com.medecineWebApp.Finance_service.mapper;

import com.medecineWebApp.Finance_service.dto.InvoiceDTO;
import com.medecineWebApp.Finance_service.models.Invoice;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    InvoiceDTO toDTO(Invoice invoice);
    @InheritInverseConfiguration
    Invoice fromDTO(InvoiceDTO dto);
}
