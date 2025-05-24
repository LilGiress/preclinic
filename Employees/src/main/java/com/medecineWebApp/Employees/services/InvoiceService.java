package com.medecineWebApp.Employees.services;

import com.medecineWebApp.Employees.models.externe.invoices.Invoice;
import com.medecineWebApp.Employees.models.externe.invoices.InvoiceItem;

import java.util.List;

public interface InvoiceService {
    List<InvoiceDTO> getAllInvoices();
    InvoiceDTO saveInvoice(Invoice invoice,List<InvoiceItem> items);
    InvoiceDTO removeInvoiceItem(Long invoiceId, Long itemId);
    InvoiceDTO updateInvoice(Long invoiceId, Invoice invoice);
    List<InvoiceDTO> getInvoicesByPatientId(Long patientId);
}
