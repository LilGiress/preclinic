package com.medecineWebApp.Finance_service.utilis;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;

import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.medecineWebApp.Finance_service.models.Payment;


import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;

public class ReceiptPdfGenerator {
    public static byte[] generateReceipt(Payment payment) {
        try {
            Document document = new Document();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, out);
            document.open();

            // Titre
            Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            Paragraph title = new Paragraph("Reçu de paiement", font);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(new Paragraph("\n"));

            // Informations de paiement
            document.add(new Paragraph("Numéro de reçu : " + payment.getInvoiceNumber()));
            document.add(new Paragraph("Date de paiement : " + payment.getPaidDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
            document.add(new Paragraph("Montant payé : " + payment.getAmount() + " €"));
            document.add(new Paragraph("Méthode de paiement : " + payment.getPaymentType()));
            document.add(new Paragraph("Facture associée : " + payment.getInvoice().getId()));

            document.add(new Paragraph("\nMerci pour votre paiement."));

            document.close();
            return out.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la génération du reçu PDF", e);
        }
    }
}

