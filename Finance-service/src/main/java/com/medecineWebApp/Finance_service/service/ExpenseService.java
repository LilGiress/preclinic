package com.medecineWebApp.Finance_service.service;

import com.medecineWebApp.Finance_service.dto.ExpenseDTO;
import com.medecineWebApp.Finance_service.enums.ExpenseCategory;
import com.medecineWebApp.Finance_service.enums.Status;
import com.medecineWebApp.Finance_service.models.Expense;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseService {

    ExpenseDTO createExpense(Expense expense) ;

     Page<ExpenseDTO> getAllExpenses(Long inventoryItem, Long userId, Status status, ExpenseCategory category, LocalDate purchaseDate,
                                     int page, int size) ;
    ExpenseDTO getExpenseById(Long id) ;

    ExpenseDTO updateExpense(Long id, Expense updated) ;
    void deleteExpense(Long id) ;

    ExpenseDTO validateExpense(Long id) ;

    ExpenseDTO rejectExpense(Long id) ;

     void uploadAttachment(Long id, MultipartFile file) ;

     List<ExpenseDTO> getExpenseReport(String fromDate, String toDate, ExpenseCategory category, Status status) ;




}
