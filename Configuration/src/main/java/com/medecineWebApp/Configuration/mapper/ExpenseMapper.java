package com.medecineWebApp.Configuration.mapper;

import com.medecineWebApp.Configuration.dto.ExpenseDTO;
import com.medecineWebApp.Configuration.models.accounts.Expense;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {
    ExpenseMapper INSTANCE = Mappers.getMapper(ExpenseMapper.class);
    ExpenseDTO expenseToExpenseDTO(Expense expense);
    Expense expenseDTOToExpense(ExpenseDTO expenseDTO);
}
