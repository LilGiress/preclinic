package com.medecineWebApp.Accounts.service;

import com.medecineWebApp.Accounts.models.Invoices;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InvoiceService {
    Invoices createInvoice(Invoices invoices);
    Invoices updateInvoice(Long id,Invoices invoices);
    Page<Invoices> findInvoicesByDateAndStatus(String startDate,String endDate, String status, int page, int size);
    void deleteInvoice(Long id);
}
