package com.medecineWebApp.Finance_service.service;

import com.medecineWebApp.Finance_service.dto.PaymentDTO;
import com.medecineWebApp.Finance_service.enums.PaymentType;
import com.medecineWebApp.Finance_service.models.Payment;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PaymentService {

     PaymentDTO createPayment(Payment payment);

     Page<PaymentDTO> getAllPayments(Long invoiceId, Long userId, int page, int size) ;

     PaymentDTO getPaymentById(Long id) ;

     PaymentDTO updatePayment(Long id, Payment updated) ;

     void deletePayment(Long id) ;

     List<PaymentDTO> getPaymentReport(String fromDate, String toDate, PaymentType type);

     byte[] generateReceipt(Long id) ;

     String getInvoiceStatus(Long invoiceId) ;
}
