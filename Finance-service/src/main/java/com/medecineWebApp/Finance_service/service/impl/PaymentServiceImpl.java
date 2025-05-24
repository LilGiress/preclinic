package com.medecineWebApp.Finance_service.service.impl;

import com.medecineWebApp.Finance_service.dto.PaymentDTO;
import com.medecineWebApp.Finance_service.enums.InvoiceStatus;
import com.medecineWebApp.Finance_service.enums.PaymentType;
import com.medecineWebApp.Finance_service.mapper.PaymentMapper;
import com.medecineWebApp.Finance_service.models.Invoice;
import com.medecineWebApp.Finance_service.models.Payment;
import com.medecineWebApp.Finance_service.repository.InvoiceRepository;
import com.medecineWebApp.Finance_service.repository.PaymentRepository;
import com.medecineWebApp.Finance_service.service.PaymentService;
import com.medecineWebApp.Finance_service.utilis.ReceiptPdfGenerator;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;

    private final PaymentMapper paymentMapper;
    private final InvoiceRepository invoiceRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository, PaymentMapper paymentMapper, InvoiceRepository invoiceRepository) {
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
        this.invoiceRepository = invoiceRepository;
    }
    @Override
    @Transactional
    public PaymentDTO createPayment(Payment payment) {
        payment.setPaidDate(LocalDate.now());
        Payment saved = paymentRepository.save(payment);

        // Mise à jour de la facture si nécessaire (logique métier)
        Invoice invoice = saved.getInvoice();
        double totalPaid = paymentRepository.sumByInvoiceId(invoice.getId());
        invoice.setTotal(totalPaid);
        invoice.setInvoiceStatus(totalPaid >= invoice.getTotal() ? InvoiceStatus.PAID : InvoiceStatus.PARTIALLY_PAID);
        invoiceRepository.save(invoice);

        return paymentMapper.toDTO(saved);
    }

    @Override
    public Page<PaymentDTO> getAllPayments(Long invoiceId, Long userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return paymentRepository.findAllByInvoiceIdAndUserId(invoiceId,userId,pageable);
    }
    @Override
    public PaymentDTO getPaymentById(Long id) {
        return paymentMapper.toDTO(paymentRepository.findById(id).orElseThrow(() -> new RuntimeException("Paiement non trouvé")));
    }
    @Override
    @Transactional
    public PaymentDTO updatePayment(Long id, Payment updated) {
        Payment existing = paymentRepository.findById(id).orElseThrow(() -> new RuntimeException("Paiement non trouvé"));
        existing.setAmount(updated.getAmount());
        existing.setPaymentType(updated.getPaymentType());
        return paymentMapper.toDTO(paymentRepository.save(existing));
    }
    @Override
    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }

    @Override
    public List<PaymentDTO> getPaymentReport(String fromDate, String toDate, PaymentType type) {
        List<Payment> payments = paymentRepository.findAll();
        return payments.stream()
                .filter(p -> (fromDate == null || !p.getPaidDate().isBefore(LocalDate.parse(fromDate))) &&
                        (toDate == null || !p.getPaidDate().isAfter(LocalDate.parse(toDate))) &&
                        (type == null || p.getPaymentType() == type))
                .map(paymentMapper::toDTO)
                .collect(Collectors.toList());
    }
    @Override
    public byte[] generateReceipt(Long id) {
        Payment payment = paymentRepository.findById(id).orElseThrow(() -> new RuntimeException("Paiement non trouvé"));
        return ReceiptPdfGenerator.generateReceipt(payment);
    }
    @Override
    public String getInvoiceStatus(Long invoiceId) {
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new RuntimeException("Facture non trouvée"));
        return invoice.getInvoiceStatus().toString();
    }
}
