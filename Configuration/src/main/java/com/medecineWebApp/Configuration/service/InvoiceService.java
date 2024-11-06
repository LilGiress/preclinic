package com.medecineWebApp.Configuration.service;


import com.medecineWebApp.Configuration.dto.InvoicesDTO;
import com.medecineWebApp.Configuration.models.accounts.Invoices;
import org.springframework.data.domain.Page;

public interface InvoiceService {
    InvoicesDTO createInvoice(Invoices invoices);
    InvoicesDTO updateInvoice(Long id,Invoices invoices);
    Page<InvoicesDTO> findInvoicesByDateAndStatus(String startDate,String endDate, String status, int page, int size);
    void deleteInvoice(Long id);
}
