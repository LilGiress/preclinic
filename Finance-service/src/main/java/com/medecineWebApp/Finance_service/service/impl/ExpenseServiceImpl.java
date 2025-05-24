package com.medecineWebApp.Finance_service.service.impl;

import com.medecineWebApp.Finance_service.dto.ExpenseDTO;
import com.medecineWebApp.Finance_service.enums.ExpenseCategory;
import com.medecineWebApp.Finance_service.enums.Status;
import com.medecineWebApp.Finance_service.filter.ExpenseSpecifications;
import com.medecineWebApp.Finance_service.mapper.ExpenseMapper;
import com.medecineWebApp.Finance_service.models.Expense;
import com.medecineWebApp.Finance_service.repository.ExpenseRepository;
import com.medecineWebApp.Finance_service.service.ExpenseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final ExpenseMapper expenseMapper;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository, ExpenseMapper expenseMapper) {
        this.expenseRepository = expenseRepository;
        this.expenseMapper = expenseMapper;
    }
    @Override
    public ExpenseDTO createExpense(Expense expense) {
        expense.setStatus(Status.PENDING);
        return expenseMapper.ExpenseToExpenseDTO(expenseRepository.save(expense));
    }
    @Override
    public Page<ExpenseDTO> getAllExpenses(Long inventoryItem, Long userId, Status status, ExpenseCategory category, LocalDate purchaseDate,
                                           int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Specification<Expense> specification = Specification.where(
                ExpenseSpecifications.byCategory(category)
                        .and(ExpenseSpecifications.byInventoryItem(inventoryItem))
                .and(ExpenseSpecifications.byUserId(userId))
                .and(ExpenseSpecifications.byStatus(status))
                .and(ExpenseSpecifications.byPurchaseDate(purchaseDate))
        );
        Page<Expense> expenses= expenseRepository.findAll(specification,pageable);
        return expenses.map(expenseMapper::ExpenseToExpenseDTO);
    }
    @Override
    public ExpenseDTO getExpenseById(Long id) {
        return expenseMapper.ExpenseToExpenseDTO(expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found with id: " + id))) ;
    }
    @Override
    public ExpenseDTO updateExpense(Long id, Expense updated) {
        Expense existing = expenseRepository.findById(id).orElseThrow(() -> new RuntimeException("Expense not found with id: " + id));
            existing.setItemName(updated.getItemName());
            existing.setPurchaseFrom(updated.getPurchaseFrom());
            existing.setPurchaseDate(updated.getPurchaseDate());
            existing.setAmount(updated.getAmount());
            existing.setPaidBy(updated.getPaidBy());
            existing.setCategory(updated.getCategory());
            existing.setInventoryItem(updated.getInventoryItem());
            existing.setAssetId(updated.getAssetId());
            existing.setEmployeId(updated.getEmployeId());
            existing.setStatus(updated.getStatus());
            return expenseMapper.ExpenseToExpenseDTO(expenseRepository.save(existing)) ;
    }
    @Override
    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }
    @Override
    public ExpenseDTO validateExpense(Long id) {
        Expense expense = expenseRepository.findById(id).orElseThrow(() -> new RuntimeException("Expense not found with id: " + id));;
        expense.setStatus(Status.APPROVED);
        return expenseMapper.ExpenseToExpenseDTO(expenseRepository.save(expense));
    }
    @Override
    public ExpenseDTO rejectExpense(Long id) {
        Expense expense = expenseRepository.findById(id).orElseThrow(() -> new RuntimeException("Expense not found with id: " + id));;
        expense.setStatus(Status.REJECTED);
        return expenseMapper.ExpenseToExpenseDTO(expenseRepository.save(expense));
    }
    @Override
    public void uploadAttachment(Long id, MultipartFile file) {
        try {
            Expense expense = expenseRepository.findById(id).orElseThrow(() -> new RuntimeException("Expense not found with id: " + id));;
            if (file != null && !file.isEmpty()) {
                // Simuler l'enregistrement d'un fichier (à remplacer par logique réelle)
                expense.setAttachment(new File(file.getOriginalFilename()));
                expenseRepository.save(expense);
            }
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'upload de la pièce jointe", e);
        }
    }

    @Override
    public List<ExpenseDTO> getExpenseReport(String fromDate, String toDate, ExpenseCategory category, Status status) {
        LocalDate start = fromDate != null ? LocalDate.parse(fromDate, DateTimeFormatter.ISO_DATE) : null;
        LocalDate end = toDate != null ? LocalDate.parse(toDate, DateTimeFormatter.ISO_DATE) : null;
        return expenseRepository.findByFilters(start, end, category, status);
    }

}
