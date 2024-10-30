package com.medecineWebApp.Configuration.service;


import com.medecineWebApp.Configuration.models.accounts.Invoices;
import org.springframework.data.domain.Page;

public interface InvoiceService {
    Invoices createInvoice(Invoices invoices);
    Invoices updateInvoice(Long id,Invoices invoices);
    Page<Invoices> findInvoicesByDateAndStatus(String startDate,String endDate, String status, int page, int size);
    void deleteInvoice(Long id);
}
