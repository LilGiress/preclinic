package com.medecineWebApp.Accounts.controller;

import com.medecineWebApp.Accounts.models.Invoices;
import com.medecineWebApp.Accounts.service.InvoiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/invoices")
public class InvoicesController {

    private final InvoiceService invoiceService;

    public InvoicesController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping("/create")
    public ResponseEntity<Invoices> createInvoice(@RequestBody Invoices invoice) {
        return ResponseEntity.ok(invoiceService.createInvoice(invoice));
    }
    @PutMapping("/update")
    public ResponseEntity<Invoices> updateInvoice(@RequestParam Long id, @RequestBody Invoices invoice) {
        return ResponseEntity.ok(invoiceService.updateInvoice(id, invoice));

    }
    @DeleteMapping("/delete")
    public void deleteInvoice(@RequestParam Long id) {
        invoiceService.deleteInvoice(id);
    }
}
