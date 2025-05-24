package com.medecineWebApp.Finance_service.controller;

import com.medecineWebApp.Finance_service.dto.InvoiceDTO;
import com.medecineWebApp.Finance_service.enums.InvoiceStatus;
import com.medecineWebApp.Finance_service.models.Invoice;
import com.medecineWebApp.Finance_service.models.InvoiceItem;
import com.medecineWebApp.Finance_service.service.InvoiceService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {
    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping
    public ResponseEntity<InvoiceDTO> createInvoice(@RequestBody Invoice invoice,
                                                    @RequestBody List<InvoiceItem> items
    ) {
        return ResponseEntity.ok(invoiceService.saveInvoice(invoice,items));
    }

    @GetMapping
    public ResponseEntity<Page<InvoiceDTO>>  getAllInvoices(
            @RequestParam(defaultValue = "0")  int page,
            @RequestParam(defaultValue = "10")  int size,
            @RequestParam InvoiceStatus status,
            @RequestParam String startDate,
            @RequestParam String endDate,
            @RequestParam Long userId) {
        return ResponseEntity.ok(invoiceService.findInvoicesByDateAndStatusAndUserId(startDate,endDate,status,userId,page,size));
    }

    @DeleteMapping("/{invoiceId}/items/{itemId}")
    public ResponseEntity<InvoiceDTO> removeInvoiceItem(
            @PathVariable Long invoiceId,
            @PathVariable Long itemId) {
        return ResponseEntity.ok(invoiceService.removeInvoiceItem(invoiceId, itemId));
    }

    @PutMapping("/{invoiceId}")
    public ResponseEntity<InvoiceDTO> updateInvoice(
            @PathVariable Long invoiceId,
            @RequestBody Invoice invoice) {
        return ResponseEntity.ok(invoiceService.updateInvoice(invoiceId, invoice));
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<InvoiceDTO>> getInvoicesByPatient(@PathVariable Long patientId) {
        return ResponseEntity.ok(invoiceService.getInvoicesByPatientId(patientId));
    }
}
