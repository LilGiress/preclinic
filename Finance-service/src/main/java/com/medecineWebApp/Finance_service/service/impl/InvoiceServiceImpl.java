package com.medecineWebApp.Finance_service.service.impl;

import com.medecineWebApp.Finance_service.dto.InvoiceDTO;
import com.medecineWebApp.Finance_service.dto.InvoiceItemDTO;
import com.medecineWebApp.Finance_service.enums.InvoiceStatus;
import com.medecineWebApp.Finance_service.filter.InvoiceSpecifications;
import com.medecineWebApp.Finance_service.mapper.InvoiceItemMapper;
import com.medecineWebApp.Finance_service.mapper.InvoiceMapper;
import com.medecineWebApp.Finance_service.models.Invoice;
import com.medecineWebApp.Finance_service.models.InvoiceItem;
import com.medecineWebApp.Finance_service.repository.InvoiceRepository;
import com.medecineWebApp.Finance_service.service.InvoiceService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final InvoiceMapper invoiceMapper;
    private final InvoiceItemMapper invoiceItemMapper;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, InvoiceMapper invoiceMapper, InvoiceItemMapper invoiceItemMapper) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceMapper = invoiceMapper;
        this.invoiceItemMapper = invoiceItemMapper;
    }

    @Override
    public List<InvoiceDTO> getAllInvoices() {
        return invoiceRepository.findAll().stream()
                .map(invoiceMapper::toDTO)
                .toList();
    }

    @Override
    public InvoiceDTO saveInvoice(Invoice invoice, List<InvoiceItem> items) {

        // Ajouter chaque item à l'invoice

        String lastInvoiceNumber = getLastInvoiceNumber();
        String newInvoiceNumber = generateInvoiceNumber(lastInvoiceNumber);
        invoice.setInvoiceNumber(newInvoiceNumber);
        items.forEach(invoice::addInvoiceItem);

        // Calculer le montant total
        double total = items.stream()
                .mapToDouble(item -> item.getUnitCost() * item.getQuantity())
                .sum();
        invoice.setTotal(total + (total * invoice.getTax() / 100) - (total * invoice.getDiscount() / 100));
        if (invoice.getUserId()!= null)
            invoice.setUserId(invoice.getUserId());
        if (invoice.getDepartmentId() != null)
            invoice.setDepartmentId(invoice.getDepartmentId());
        return invoiceMapper.toDTO(invoiceRepository.save(invoice));

    }

    @Override
    @Transactional
    public InvoiceDTO removeInvoiceItem(Long invoiceId, Long itemId) {
        // Récupérer la facture
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));

        // Trouver l'item à supprimer
        InvoiceItem itemToRemove = invoice.getInvoiceItems().stream()
                .filter(item -> item.getId().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("InvoiceItem not found"));

        // Retirer l'item de la facture
        invoice.removeInvoiceItem(itemToRemove);

        // Sauvegarder la facture (orphanRemoval s'occupera de supprimer l'item)
        return invoiceMapper.toDTO(invoiceRepository.save(invoice));
    }

    @Override
    @Transactional
    public InvoiceDTO updateInvoice(Long invoiceId, Invoice invoice) {
//
        // Récupérer l'Invoice existante
        Invoice existingInvoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));

        // Mettre à jour les champs de l'Invoice
        if (invoice.getDepartmentId() != null) {
            existingInvoice.setDepartmentId(invoice.getDepartmentId());
        }
        if (invoice.getUserId() != null) {
            existingInvoice.setUserId(invoice.getUserId());
            existingInvoice.setEmail(invoice.getEmail());
        }

        existingInvoice.setInvoiceDate(invoice.getInvoiceDate());
        existingInvoice.setDueDate(invoice.getDueDate());
        existingInvoice.setTax(invoice.getTax());
        existingInvoice.setDiscount(invoice.getDiscount());


        // Gestion des InvoiceItems
        List<InvoiceItemDTO> updatedItems = invoice.getInvoiceItems().stream().map(
                invoiceItemMapper::invoiceItemToInvoiceItemDTO
        ).toList();
        List<InvoiceItem> existingItems = existingInvoice.getInvoiceItems();

        // Supprimer les items non présents dans le DTO
        List<InvoiceItem> itemsToRemove = existingItems.stream()
                .filter(item -> updatedItems.stream().noneMatch(dto -> dto.getId() != null && dto.getId().equals(item.getId())))
                .toList();

        itemsToRemove.forEach(existingInvoice::removeInvoiceItem);

        // Mettre à jour ou ajouter de nouveaux items
        updatedItems.forEach(dto -> {
            if (dto.getId() == null) {
                // Nouvel item
                InvoiceItem newItem = new InvoiceItem();
                newItem.setDescription(dto.getDescription());
                newItem.setUnitCost(dto.getUnitCost());
                newItem.setQuantity(dto.getQuantity());
                newItem.setItemName(dto.getItemName());
                newItem.setAmount(dto.getAmount());
                existingInvoice.addInvoiceItem(newItem);
            } else {
                // Mise à jour d'un item existant
                InvoiceItem existingItem = existingItems.stream()
                        .filter(item -> item.getId().equals(dto.getId()))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("InvoiceItem not found"));

                existingItem.setDescription(dto.getDescription());
                existingItem.setUnitCost(dto.getUnitCost());
                existingItem.setQuantity(dto.getQuantity());
                existingItem.setItemName(dto.getItemName());
                existingItem.setAmount(dto.getAmount());

            }
        });

        // Calculer le montant total
        double totalAmount = existingInvoice.getInvoiceItems().stream()
                .mapToDouble(item -> item.getUnitCost() * item.getQuantity())
                .sum();

        existingInvoice.setTotal(totalAmount+ (totalAmount * existingInvoice.getTax() / 100) - (totalAmount * existingInvoice.getDiscount() / 100));

        // Sauvegarder et retourner l'Invoice mise à jour
        return invoiceMapper.toDTO(invoiceRepository.save(existingInvoice));
    }

    @Override
    public List<InvoiceDTO> getInvoicesByPatientId(Long patientId) {
        if (patientId == null) {
            throw new RuntimeException("Patient not found");
        }
        return invoiceRepository.findByUserId(patientId).stream()
                .map(invoiceMapper::toDTO)
                .toList();
    }

    @Override
    public Page<InvoiceDTO> findInvoicesByDateAndStatusAndUserId(String startDate, String endDate, InvoiceStatus status,Long userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Specification<Invoice> specification= Specification.where(
               InvoiceSpecifications.byUserId(userId)
                        .and(InvoiceSpecifications.byEnDate(endDate))
                        .or(InvoiceSpecifications.byStartDate(startDate))
                        .or(InvoiceSpecifications.byStatus(status))

        );
        Page<Invoice> invoices = invoiceRepository.findAll(specification, pageable);

        return invoices.map(invoiceMapper::toDTO);
    }


    // Method to find the last invoice number from the database
    private String getLastInvoiceNumber() {
        // Find the latest invoice (sorted by ID or creation date)
        Optional<Invoice> lastInvoice = invoiceRepository.findTopByOrderByIdDesc();
        return lastInvoice.map(Invoice::getInvoiceNumber).orElse(null);
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
