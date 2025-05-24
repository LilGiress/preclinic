package com.medecineWebApp.Finance_service.controller;

import com.medecineWebApp.Finance_service.dto.ExpenseDTO;
import com.medecineWebApp.Finance_service.enums.ExpenseCategory;
import com.medecineWebApp.Finance_service.enums.Status;
import com.medecineWebApp.Finance_service.models.Expense;
import com.medecineWebApp.Finance_service.service.ExpenseService;
import com.medecineWebApp.Finance_service.service.impl.ExpenseServiceImpl;
import com.medecineWebApp.Finance_service.utilis.ExpensePdfGenerator;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;
    private final ExpenseServiceImpl expenseServiceImpl;

    public ExpenseController(ExpenseService expenseService, ExpenseServiceImpl expenseServiceImpl) {
        this.expenseService = expenseService;
        this.expenseServiceImpl = expenseServiceImpl;

    }

    @PostMapping
    public ResponseEntity<ExpenseDTO> createExpense(@RequestBody Expense expense) {
        return ResponseEntity.ok(expenseService.createExpense(expense));
    }

    @GetMapping
    public ResponseEntity<Page<ExpenseDTO>> getAllExpenses(
            @RequestParam(required = false) Long inventoryItem,
            @RequestParam Long userId,
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) ExpenseCategory category,
            @RequestParam LocalDate purchaseDate,
            @RequestParam(defaultValue = "0")  int page,
            @RequestParam(defaultValue = "10")  int size
    ) {
        return ResponseEntity.ok(expenseService.getAllExpenses(inventoryItem,userId,status,category,purchaseDate,page,size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseDTO> getExpenseById(@PathVariable Long id) {
        return ResponseEntity.ok(expenseService.getExpenseById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpenseDTO> updateExpense(
            @PathVariable Long id,
            @RequestBody Expense expense
    ) {
        return ResponseEntity.ok(expenseService.updateExpense(id, expense));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/validate")
    public ResponseEntity<ExpenseDTO> validateExpense(@PathVariable Long id) {
        return ResponseEntity.ok(expenseService.validateExpense(id));
    }

    @PostMapping("/{id}/reject")
    public ResponseEntity<ExpenseDTO> rejectExpense(@PathVariable Long id) {
        return ResponseEntity.ok(expenseService.rejectExpense(id));
    }

    @PostMapping("/{id}/attachment")
    public ResponseEntity<String> uploadAttachment(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file) {
        expenseService.uploadAttachment(id, file);
        return ResponseEntity.ok("Pièce jointe ajoutée avec succès");
    }

//    @GetMapping("/user/{userId}")
//    public ResponseEntity<List<ExpenseDTO>> getByUser(@PathVariable Long userId) {
//        return ResponseEntity.ok(expenseService.getExpensesByUser(userId));
//    }
//
//    @GetMapping("/employe/{employeId}")
//    public ResponseEntity<List<ExpenseDTO>> getByEmploye(@PathVariable Long employeId) {
//        return ResponseEntity.ok(expenseService.getExpensesByEmploye(employeId));
//    }
//
//    @GetMapping("/inventory/{itemId}")
//    public ResponseEntity<List<ExpenseDTO>> getByInventoryItem(@PathVariable Long itemId) {
//        return ResponseEntity.ok(expenseService.getExpensesByInventoryItem(itemId));
//    }
//
//    @GetMapping("/asset/{assetId}")
//    public ResponseEntity<List<ExpenseDTO>> getByAsset(@PathVariable Long assetId) {
//        return ResponseEntity.ok(expenseService.getExpensesByAsset(assetId));
//    }
//
//    @GetMapping("/category/{category}")
//    public ResponseEntity<List<ExpenseDTO>> getByCategory(@PathVariable ExpenseCategory category) {
//        return ResponseEntity.ok(expenseService.getExpensesByCategory(category));
//    }
//
//    @GetMapping("/status/{status}")
//    public ResponseEntity<List<ExpenseDTO>> getByStatus(@PathVariable Status status) {
//        return ResponseEntity.ok(expenseService.getExpensesByStatus(status));
//    }

    @GetMapping("/report")
    public ResponseEntity<List<ExpenseDTO>> getExpenseReport(@RequestParam(required = false) String fromDate,
                                                          @RequestParam(required = false) String toDate,
                                                          @RequestParam(required = false) ExpenseCategory category,
                                                          @RequestParam(required = false) Status status) {
        return ResponseEntity.ok(expenseService.getExpenseReport(fromDate, toDate, category, status));
    }

//    @GetMapping("/export/pdf")
//    public ResponseEntity<byte[]> exportExpensesPdf() {
//        byte[] pdf = expenseServiceImpl.generateExpensesPdf();
//        return ResponseEntity.ok()
//                .contentType(MediaType.APPLICATION_PDF)
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=expenses.pdf")
//                .body(pdf);
//    }

    @GetMapping("/export/pdf/{id}")
    public ResponseEntity<byte[]> downloadExpensePdf(@PathVariable Long id) {
        try {
            ExpenseDTO expense = expenseService.getExpenseById(id); // Récupère la dépense
            byte[] logoBytes = Files.readAllBytes(Paths.get("src/main/resources/static/logo-clinic.png"));
            byte[] pdfBytes = ExpensePdfGenerator.generateExpensePdf(expense, logoBytes);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "depense-" + id + ".pdf");

            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
