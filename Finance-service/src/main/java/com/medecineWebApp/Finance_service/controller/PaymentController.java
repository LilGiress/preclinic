package com.medecineWebApp.Finance_service.controller;

import com.medecineWebApp.Finance_service.dto.PaymentDTO;
import com.medecineWebApp.Finance_service.enums.PaymentType;
import com.medecineWebApp.Finance_service.models.Payment;
import com.medecineWebApp.Finance_service.service.PaymentService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<PaymentDTO> createPayment(@RequestBody Payment payment) {
        return ResponseEntity.ok(paymentService.createPayment(payment));
    }

    @GetMapping
    public ResponseEntity<Page<PaymentDTO>> getAllPayments(
            @RequestParam Long invoiceId,
            @RequestParam Long userId,
            @RequestParam(defaultValue = "0")  int page,
            @RequestParam(defaultValue = "10")  int size
    ) {
        return ResponseEntity.ok(paymentService.getAllPayments(invoiceId,userId,page,size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDTO> getPaymentById(@PathVariable Long id) {
        return ResponseEntity.ok(paymentService.getPaymentById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentDTO> updatePayment(@PathVariable Long id, @RequestBody Payment payment) {
        return ResponseEntity.ok(paymentService.updatePayment(id, payment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/report")
    public ResponseEntity<List<PaymentDTO>> getPaymentReport(
            @RequestParam(required = false) String fromDate,
            @RequestParam(required = false) String toDate,
            @RequestParam(required = false) PaymentType type
    ) {
        return ResponseEntity.ok(paymentService.getPaymentReport(fromDate, toDate, type));
    }

    @GetMapping("/receipt/{id}")
    public ResponseEntity<byte[]> getPaymentReceipt(@PathVariable Long id) {
        byte[] pdf = paymentService.generateReceipt(id);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=recu_" + id + ".pdf")
                .body(pdf);
    }

    @GetMapping("/status/{invoiceId}")
    public ResponseEntity<String> getInvoicePaymentStatus(@PathVariable Long invoiceId) {
        return ResponseEntity.ok(paymentService.getInvoiceStatus(invoiceId));
    }
}
