package com.medecineWebApp.Configuration.service.impl;


import com.medecineWebApp.Configuration.dto.InvoicesDTO;
import com.medecineWebApp.Configuration.mapper.InvoicesMapper;
import com.medecineWebApp.Configuration.models.accounts.Invoices;
import com.medecineWebApp.Configuration.repository.account.InvoicesRepository;
import com.medecineWebApp.Configuration.service.InvoiceService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Service
public class InvoicesServiceImpl implements InvoiceService {
    private final InvoicesRepository invoicesRepository;
    private final InvoicesMapper invoicesMapper;

    public InvoicesServiceImpl(InvoicesRepository invoicesRepository, InvoicesMapper invoicesMapper) {
        this.invoicesRepository = invoicesRepository;
        this.invoicesMapper = invoicesMapper;
    }

    @Override
    public InvoicesDTO createInvoice(Invoices invoices) {
        String lastInvoiceNumber = getLastInvoiceNumber();
        String newInvoiceNumber = generateInvoiceNumber(lastInvoiceNumber);
       // invoices.setInvoiceNumber(newInvoiceNumber);
        Invoices create = new Invoices();
        create.setInvoiceNumber(newInvoiceNumber);
        create.setInvoiceDate(LocalDate.now());
        create.setInvoiceStatus(invoices.getInvoiceStatus());
        create.setEmail(invoices.getEmail());
        create.setTaxId(invoices.getTaxId());
        create.setPatientId(invoices.getPatientId());
        create.setOtherInformation(invoices.getOtherInformation());
        create.setDepartmentId(invoices.getDepartmentId());
        create.setInvoicedatedue(invoices.getInvoicedatedue());
        create.setBillingAddress(invoices.getBillingAddress());
        create.setInvoiceStatus(invoices.getInvoiceStatus());
        Invoices saveInvoice = invoicesRepository.save(create);
        return invoicesMapper.invoicesToInvoicesDTO(saveInvoice);
    }

    @Override
    public InvoicesDTO updateInvoice(Long id, Invoices invoices) {
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

            Invoices saveInvoice = invoicesRepository.save(invoice);
            return invoicesMapper.invoicesToInvoicesDTO(saveInvoice);
        }
       throw new RuntimeException("Invoice not found");
    }

    @Override
    public Page<InvoicesDTO> findInvoicesByDateAndStatus(String startDate, String endDate, String status, int page, int size) {

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
