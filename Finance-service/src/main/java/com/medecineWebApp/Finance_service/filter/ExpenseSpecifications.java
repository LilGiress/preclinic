package com.medecineWebApp.Finance_service.filter;


import com.medecineWebApp.Finance_service.enums.ExpenseCategory;
import com.medecineWebApp.Finance_service.enums.Status;
import com.medecineWebApp.Finance_service.models.Expense;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class ExpenseSpecifications {
    public static Specification<Expense> byStatus(Status status) {
        return ((root, query, criteriaBuilder) ->
                status == null ? null :    criteriaBuilder.equal(root.get("status"), status));
    }

    public static Specification<Expense> byCategory(ExpenseCategory category) {
        return ((root, query, criteriaBuilder) ->
                category == null ? null :    criteriaBuilder.equal(root.get("category"), category));
    }

    public static Specification<Expense> byUserId(Long userId) {
        return ((root, query, criteriaBuilder) ->
                userId == null ? null :    criteriaBuilder.equal(root.get("userId"), userId));
    }

    public static Specification<Expense> byInventoryItem(Long inventoryItem) {
        return ((root, query, criteriaBuilder) ->
                inventoryItem == null ? null :    criteriaBuilder.equal(root.get("inventoryItem"), inventoryItem));
    }

    public static Specification<Expense> byPurchaseDate(LocalDate purchaseDate) {
        return ((root, query, criteriaBuilder) ->
                purchaseDate == null ? null :    criteriaBuilder.equal(root.get("purchaseDate"), purchaseDate));
    }
}
