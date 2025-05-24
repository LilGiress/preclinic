package com.medecineWebApp.Finance_service.mapper;



import com.medecineWebApp.Finance_service.dto.InvoiceItemDTO;
import com.medecineWebApp.Finance_service.models.InvoiceItem;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InvoiceItemMapper {
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    InvoiceItemDTO invoiceItemToInvoiceItemDTO(InvoiceItem invoiceItem);
    @InheritInverseConfiguration
    InvoiceItem invoiceItemDTOToInvoiceItem(InvoiceItemDTO invoiceItemDTO);
}
