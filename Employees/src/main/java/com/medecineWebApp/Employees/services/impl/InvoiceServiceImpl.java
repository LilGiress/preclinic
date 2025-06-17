//package com.medecineWebApp.Employees.services.impl;
//
//import com.medecineWebApp.Employees.feignClient.DepartmentClient;
//import com.medecineWebApp.Employees.feignClient.PatientClient;
//import com.medecineWebApp.Employees.repository.InvoiceRepository;
//import com.medecineWebApp.Employees.services.InvoiceService;
//import jakarta.transaction.Transactional;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//@Service
//public class InvoiceServiceImpl implements InvoiceService {
//    private final InvoiceRepository invoiceRepository;
//    private final InvoiceMapper invoiceMapper;
//    private final InvoiceItemMapper invoiceItemMapper;
//    private final PatientClient patientClient;
//    private final DepartmentClient departmentClient;
//
//    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, InvoiceMapper invoiceMapper, InvoiceItemMapper invoiceItemMapper, PatientClient patientClient, DepartmentClient departmentClient) {
//        this.invoiceRepository = invoiceRepository;
//        this.invoiceMapper = invoiceMapper;
//        this.invoiceItemMapper = invoiceItemMapper;
//        this.patientClient = patientClient;
//        this.departmentClient = departmentClient;
//    }
//
//    @Override
//    public List<InvoiceDTO> getAllInvoices() {
//        return invoiceRepository.findAll().stream()
//                .map(invoiceMapper::toInvoiceDTO)
//                .toList();
//    }
//
//    @Override
//    public InvoiceDTO saveInvoice(Invoice invoice,List<InvoiceItem> items) {
//       // Patient patient = patientClient.getPatientById(invoice.getPatientId());
//      //  Departement departement= departmentClient.getDepartment(invoice.getDepartmentId());
//        // Ajouter chaque item à l'invoice
//        items.forEach(invoice::addInvoiceItem);
//
//        // Calculer le montant total
//        double total = items.stream()
//                .mapToDouble(item -> item.getUnitCost() * item.getQuantity())
//                .sum();
//        invoice.setTotal(total + (total * invoice.getTax() / 100) - (total * invoice.getDiscount() / 100));
//        if (invoice.getPatientId()!= null)
//            invoice.setPatientId(invoice.getPatientId());
//        if (invoice.getDepartmentId() != null)
//            invoice.setDepartmentId(invoice.getDepartmentId());
//        return invoiceMapper.toInvoiceDTO(invoiceRepository.save(invoice));
//
//    }
//
//    @Override
//    @Transactional
//    public InvoiceDTO removeInvoiceItem(Long invoiceId, Long itemId) {
//        // Récupérer la facture
//        Invoice invoice = invoiceRepository.findById(invoiceId)
//                .orElseThrow(() -> new RuntimeException("Invoice not found"));
//
//        // Trouver l'item à supprimer
//        InvoiceItem itemToRemove = invoice.getInvoiceItems().stream()
//                .filter(item -> item.getId().equals(itemId))
//                .findFirst()
//                .orElseThrow(() -> new RuntimeException("InvoiceItem not found"));
//
//        // Retirer l'item de la facture
//        invoice.removeInvoiceItem(itemToRemove);
//
//        // Sauvegarder la facture (orphanRemoval s'occupera de supprimer l'item)
//        return invoiceMapper.toInvoiceDTO(invoiceRepository.save(invoice));
//    }
//
//    @Override
//    @Transactional
//    public InvoiceDTO updateInvoice(Long invoiceId, Invoice invoice) {
////        Departement departement= departmentClient.getDepartment(invoice.getDepartmentId());
////        Patient patient=patientClient.getPatientById(invoice.getPatientId());
//        // Récupérer l'Invoice existante
//        Invoice existingInvoice = invoiceRepository.findById(invoiceId)
//                .orElseThrow(() -> new RuntimeException("Invoice not found"));
//
//        // Mettre à jour les champs de l'Invoice
//        if (invoice.getDepartmentId() != null) {
//            existingInvoice.setDepartmentId(invoice.getDepartmentId());
//        }
//        if (invoice.getPatientId() != null) {
//            existingInvoice.setPatientId(invoice.getPatientId());
//            existingInvoice.setEmail(invoice.getEmail());
//        }
//
//
//
//        existingInvoice.setInvoiceDate(invoice.getInvoiceDate());
//        existingInvoice.setDueDate(invoice.getDueDate());
//        existingInvoice.setTax(invoice.getTax());
//        existingInvoice.setDiscount(invoice.getDiscount());
//
//
//        // Gestion des InvoiceItems
//        List<InvoiceItemDTO> updatedItems = invoice.getInvoiceItems().stream().map(
//            invoiceItemMapper::invoiceItemToInvoiceItemDTO
//        ).toList();
//        List<InvoiceItem> existingItems = existingInvoice.getInvoiceItems();
//
//        // Supprimer les items non présents dans le DTO
//        List<InvoiceItem> itemsToRemove = existingItems.stream()
//                .filter(item -> updatedItems.stream().noneMatch(dto -> dto.getId() != null && dto.getId().equals(item.getId())))
//                .toList();
//
//        itemsToRemove.forEach(existingInvoice::removeInvoiceItem);
//
//        // Mettre à jour ou ajouter de nouveaux items
//        updatedItems.forEach(dto -> {
//            if (dto.getId() == null) {
//                // Nouvel item
//                InvoiceItem newItem = new InvoiceItem();
//                newItem.setDescription(dto.getDescription());
//                newItem.setUnitCost(dto.getUnitCost());
//                newItem.setQuantity(dto.getQuantity());
//                newItem.setItemName(dto.getItemName());
//                newItem.setAmount(dto.getAmount());
//                existingInvoice.addInvoiceItem(newItem);
//            } else {
//                // Mise à jour d'un item existant
//                InvoiceItem existingItem = existingItems.stream()
//                        .filter(item -> item.getId().equals(dto.getId()))
//                        .findFirst()
//                        .orElseThrow(() -> new RuntimeException("InvoiceItem not found"));
//
//                existingItem.setDescription(dto.getDescription());
//                existingItem.setUnitCost(dto.getUnitCost());
//                existingItem.setQuantity(dto.getQuantity());
//                existingItem.setItemName(dto.getItemName());
//                existingItem.setAmount(dto.getAmount());
//
//            }
//        });
//
//        // Calculer le montant total
//        double totalAmount = existingInvoice.getInvoiceItems().stream()
//                .mapToDouble(item -> item.getUnitCost() * item.getQuantity())
//                .sum();
//
//        existingInvoice.setTotal(totalAmount+ (totalAmount * existingInvoice.getTax() / 100) - (totalAmount * existingInvoice.getDiscount() / 100));
//
//        // Sauvegarder et retourner l'Invoice mise à jour
//        return invoiceMapper.toInvoiceDTO(invoiceRepository.save(existingInvoice));
//    }
//
//    @Override
//    public List<InvoiceDTO> getInvoicesByPatientId(Long patientId) {
//       // Patient patient = patientClient.getPatientById(patientId);
//        if (patientId == null) {
//            throw new RuntimeException("Patient not found");
//        }
//        return invoiceRepository.findByPatientId(patientId).stream()
//                .map(invoiceMapper::toInvoiceDTO)
//                .toList();
//    }
//}
