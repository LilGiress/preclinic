package com.medecineWebApp.Configuration.mapper;

import com.medecineWebApp.Configuration.dto.InvoicesDTO;
import com.medecineWebApp.Configuration.models.accounts.Invoices;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface InvoicesMapper {
    InvoicesMapper INSTANCE = Mappers.getMapper(InvoicesMapper.class);
    InvoicesDTO invoicesToInvoicesDTO(Invoices invoices);
    Invoices invoicesDTOToInvoices(InvoicesDTO invoicesDTO);
}
