package com.medecineWebApp.Finance_service.repository;

import com.medecineWebApp.Finance_service.dto.ExpenseDTO;
import com.medecineWebApp.Finance_service.enums.ExpenseCategory;
import com.medecineWebApp.Finance_service.enums.Status;
import com.medecineWebApp.Finance_service.models.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> , JpaSpecificationExecutor<Expense> {
    List<ExpenseDTO> findByFilters(LocalDate start, LocalDate end, ExpenseCategory category, Status status);
}
