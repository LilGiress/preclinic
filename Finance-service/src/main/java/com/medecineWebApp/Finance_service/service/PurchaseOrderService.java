package com.medecineWebApp.Finance_service.service;

import com.medecineWebApp.Finance_service.dto.PurchaseOrderDTO;
import com.medecineWebApp.Finance_service.models.PurchaseOrder;

import java.util.List;


public interface PurchaseOrderService {

     PurchaseOrderDTO create(PurchaseOrder order) ;

//     List<PurchaseOrderDTO> getAll() ;

     PurchaseOrderDTO getById(Long id) ;

     PurchaseOrderDTO update(Long id, PurchaseOrder updated) ;

     void delete(Long id);

     PurchaseOrderDTO validateOrder(Long id) ;

     PurchaseOrderDTO cancelOrder(Long id);

     PurchaseOrderDTO receiveOrder(Long id) ;
//     ResponseEntity<byte[]> exportPdf(Long id) ;
//     List<PurchaseOrderDTO> getHistory(String fromDate, String toDate, String status) ;
}
