package com.medecineWebApp.Finance_service.service;

import com.medecineWebApp.Finance_service.dto.InvoiceDTO;
import com.medecineWebApp.Finance_service.enums.InvoiceStatus;
import com.medecineWebApp.Finance_service.models.Invoice;
import com.medecineWebApp.Finance_service.models.InvoiceItem;
import org.springframework.data.domain.Page;

import java.util.List;

public interface InvoiceService {
    List<InvoiceDTO> getAllInvoices();
    InvoiceDTO saveInvoice(Invoice invoice, List<InvoiceItem> items);
    InvoiceDTO removeInvoiceItem(Long invoiceId, Long itemId);
    InvoiceDTO updateInvoice(Long invoiceId, Invoice invoice);
    List<InvoiceDTO> getInvoicesByPatientId(Long patientId);
    Page<InvoiceDTO> findInvoicesByDateAndStatusAndUserId(String startDate, String endDate, InvoiceStatus status, Long userId,int page, int size);
}
