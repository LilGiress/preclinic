package com.medecineWebApp.Finance_service.service.impl;

import com.medecineWebApp.Finance_service.dto.PurchaseOrderDTO;
import com.medecineWebApp.Finance_service.enums.OrderStatus;
import com.medecineWebApp.Finance_service.mapper.PurchaseOrderMapper;
import com.medecineWebApp.Finance_service.models.PurchaseOrder;
import com.medecineWebApp.Finance_service.repository.PurchaseOrderRepository;
import com.medecineWebApp.Finance_service.service.PurchaseOrderService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {
    private final PurchaseOrderRepository purchaseOrderRepository;
    private final PurchaseOrderMapper purchaseOrderMapper;

    public PurchaseOrderServiceImpl(PurchaseOrderRepository purchaseOrderRepository, PurchaseOrderMapper purchaseOrderMapper) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.purchaseOrderMapper = purchaseOrderMapper;
    }

    @Transactional
    public PurchaseOrderDTO create(PurchaseOrder order) {
        order.setStatus(OrderStatus.PENDING);
        order.setOrderDate(LocalDate.now());
        order.getPurchaseItems().forEach(item -> item.setPurchaseOrder(order));
        return purchaseOrderMapper.fromDTO(purchaseOrderRepository.save(order));
    }

//    public List<PurchaseOrderDTO> getAll() {
//        return purchaseOrderRepository.findAll();
//    }

    public PurchaseOrderDTO getById(Long id) {
        return purchaseOrderMapper.fromDTO(purchaseOrderRepository.findById(id).orElseThrow(() -> new RuntimeException("Commande non trouvée")));
    }

    @Transactional
    public PurchaseOrderDTO update(Long id, PurchaseOrder updated) {
        PurchaseOrder order = purchaseOrderRepository.findById(id).orElseThrow(() -> new RuntimeException("Commande non trouvée"));
        order.setTotalAmount(updated.getTotalAmount());
        order.setPurchaseItems(updated.getPurchaseItems());
        order.getPurchaseItems().forEach(item -> item.setPurchaseOrder(order));
        return purchaseOrderMapper.fromDTO(purchaseOrderRepository.save(order));
    }

    @Transactional
    public void delete(Long id) {
        purchaseOrderRepository.deleteById(id);
    }

    @Transactional
    public PurchaseOrderDTO validateOrder(Long id) {
        PurchaseOrder order = purchaseOrderRepository.findById(id).orElseThrow(() -> new RuntimeException("Commande non trouvée"));
        order.setStatus(OrderStatus.VALIDATED);
        return purchaseOrderMapper.fromDTO(purchaseOrderRepository.save(order));
    }

    @Transactional
    public PurchaseOrderDTO cancelOrder(Long id) {
        PurchaseOrder order = purchaseOrderRepository.findById(id).orElseThrow(() -> new RuntimeException("Commande non trouvée"));
        order.setStatus(OrderStatus.CANCELLED);
        return purchaseOrderMapper.fromDTO(purchaseOrderRepository.save(order));
    }

    @Transactional
    public PurchaseOrderDTO receiveOrder(Long id) {
        PurchaseOrder order = purchaseOrderRepository.findById(id).orElseThrow(() -> new RuntimeException("Commande non trouvée"));
        order.setStatus(OrderStatus.RECEIVED);
        return purchaseOrderMapper.fromDTO(purchaseOrderRepository.save(order));
    }

//    public ResponseEntity<byte[]> exportPdf(Long id) {
//        PurchaseOrder order = purchaseOrderRepository.findById(id).orElseThrow(() -> new RuntimeException("Commande non trouvée"));
//        byte[] pdf = PdfGenerator.generatePurchaseOrderPdf(order);
//
//        return ResponseEntity.ok()
//                .contentType(MediaType.APPLICATION_PDF)
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=commande_" + id + ".pdf")
//                .body(pdf);
//    }

//    public List<PurchaseOrderDTO> getHistory(String fromDate, String toDate, String status) {
//        // Implémentation simplifiée, à adapter avec une méthode personnalisée dans le repo
//        return purchaseOrderRepository.findAll();
//    }
}
