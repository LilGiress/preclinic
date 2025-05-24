package com.medecineWebApp.Finance_service.utilis;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.medecineWebApp.Finance_service.dto.ExpenseDTO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class ExpensePdfGenerator {
    public static byte[] generateExpensePdf(ExpenseDTO expense, byte[] logoBytes) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document(PageSize.A4, 36, 36, 90, 36);
        PdfWriter writer = PdfWriter.getInstance(document, baos);
        document.open();

        // Ajouter le logo
        if (logoBytes != null) {
            Image logo = Image.getInstance(logoBytes);
            logo.scaleToFit(100, 100);
            logo.setAlignment(Element.ALIGN_LEFT);
            document.add(logo);
        }

        // Titre
        Paragraph title = new Paragraph("Reçu de Dépense", new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD));
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(20f);
        document.add(title);

        // Encadré de détails
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);

        table.addCell(cell("Article :", true));
        table.addCell(cell(expense.getItemName(), false));

        table.addCell(cell("Acheté chez :", true));
        table.addCell(cell(expense.getPurchaseFrom(), false));

        table.addCell(cell("Date :", true));
        table.addCell(cell(expense.getPurchaseDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), false));

        table.addCell(cell("Montant :", true));
        table.addCell(cell(String.format("%.2f €", expense.getAmount()), false));

        table.addCell(cell("Payé par :", true));
        table.addCell(cell(String.valueOf(expense.getPaidBy()), false));

        table.addCell(cell("Catégorie :", true));
        table.addCell(cell(String.valueOf(expense.getCategory()), false));

        table.addCell(cell("Statut :", true));
        table.addCell(cell(String.valueOf(expense.getStatus()), false));

        document.add(table);

        // Ajouter un QR code
        Image qrCode = generateQrCodeImage("DEPENSE-" + expense.getId(), 120, 120);
        qrCode.setAlignment(Element.ALIGN_RIGHT);
        document.add(qrCode);

        document.close();
        return baos.toByteArray();
    }

    private static PdfPCell cell(String content, boolean isHeader) {
        Font font = isHeader ? new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD) :
                new Font(Font.FontFamily.HELVETICA, 12);
        PdfPCell cell = new PdfPCell(new Phrase(content, font));
        cell.setPadding(8f);
        if (isHeader) {
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        }
        return cell;
    }

    private static Image generateQrCodeImage(String data, int width, int height) throws WriterException, IOException, BadElementException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, width, height);
        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
        return Image.getInstance(pngOutputStream.toByteArray());
    }
}
