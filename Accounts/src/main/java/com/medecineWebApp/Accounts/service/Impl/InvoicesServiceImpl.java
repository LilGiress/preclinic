package com.medecineWebApp.Accounts.service.Impl;

import com.medecineWebApp.Accounts.models.Invoices;
import com.medecineWebApp.Accounts.repositories.InvoicesRepository;
import com.medecineWebApp.Accounts.service.InvoiceService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InvoicesServiceImpl implements InvoiceService {
    private final InvoicesRepository invoicesRepository;

    public InvoicesServiceImpl(InvoicesRepository invoicesRepository) {
        this.invoicesRepository = invoicesRepository;
    }

    @Override
    public Invoices createInvoice(Invoices invoices) {
        String lastInvoiceNumber = getLastInvoiceNumber();
        String newInvoiceNumber = generateInvoiceNumber(lastInvoiceNumber);
        invoices.setInvoiceNumber(newInvoiceNumber);
        return invoicesRepository.save(invoices);
    }

    @Override
    public Invoices updateInvoice(Long id, Invoices invoices) {
        Optional<Invoices> invoicesOptional = invoicesRepository.findById(id);
        if (invoicesOptional.isPresent()) {
            Invoices invoice = invoicesOptional.get();
            invoice.setInvoiceNumber(generateInvoiceNumber(invoice.getInvoiceNumber()));
            invoice.setInvoiceDate(invoice.getInvoiceDate());
            invoice.setInvoiceStatus(invoice.getInvoiceStatus());
            invoice.setEmail(invoice.getEmail());
            invoice.setBillingAddress(invoice.getBillingAddress());
            invoice.setDepartmentId(invoice.getDepartmentId());
            invoice.setOtherInformation(invoice.getOtherInformation());
            invoice.setTaxId(invoice.getTaxId());
            invoice.setInvoicedatedue(invoice.getInvoicedatedue());
            invoice.setPatientId(invoice.getPatientId());
            return invoicesRepository.save(invoices);
        }
       throw new RuntimeException("Invoice not found");
    }

    @Override
    public Page<Invoices> findInvoicesByDateAndStatus(String startDate, String endDate, String status, int page, int size) {
        return null;
    }

    @Override
    public void deleteInvoice(Long id) {
        invoicesRepository.deleteById(id);

    }

    // Method to find the last invoice number from the database
    private String getLastInvoiceNumber() {
        // Find the latest invoice (sorted by ID or creation date)
        Optional<Invoices> lastInvoice = invoicesRepository.findTopByOrderByIdDesc();
        return lastInvoice.isPresent() ? lastInvoice.get().getInvoiceNumber() : null;
    }

    // Method to generate a new invoice number like #INV-0001
    private String generateInvoiceNumber(String lastInvoiceNumber) {
        int nextNumber = 1;

        if (lastInvoiceNumber != null && lastInvoiceNumber.startsWith("#INV-")) {
            String numberPart = lastInvoiceNumber.substring(5); // Extract numeric part after #INV-
            nextNumber = Integer.parseInt(numberPart) + 1; // Increment the number
        }

        return String.format("#INV-%04d", nextNumber); // Format as #INV-XXXX
    }

}
