package com.medecineWebApp.Finance_service.mapper;

import com.medecineWebApp.Finance_service.dto.ExpenseDTO;
import com.medecineWebApp.Finance_service.models.Expense;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    ExpenseDTO ExpenseToExpenseDTO(Expense expense);
    @InheritInverseConfiguration
    Expense ExpenseDTOToExpense(ExpenseDTO expenseDTO);

}
