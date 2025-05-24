package com.medecineWebApp.Finance_service.filter;

import com.medecineWebApp.Finance_service.enums.InvoiceStatus;
import com.medecineWebApp.Finance_service.models.Invoice;
import org.springframework.data.jpa.domain.Specification;

public class InvoiceSpecifications {
    public static Specification<Invoice> byStatus(InvoiceStatus status) {
        return ((root, query, criteriaBuilder) ->
                status == null ? null :    criteriaBuilder.equal(root.get("status"), status));
    }

    public static Specification<Invoice> byStartDate(String startDate) {
        return ((root, query, criteriaBuilder) ->
                startDate == null ? null :    criteriaBuilder.equal(root.get("startDate"), startDate));
    }

    public static Specification<Invoice> byEnDate(String endDate) {
        return ((root, query, criteriaBuilder) ->
                endDate == null ? null :    criteriaBuilder.equal(root.get("endDate"), endDate));
    }

    public static Specification<Invoice> byUserId(Long userId) {
        return ((root, query, criteriaBuilder) ->
                userId == null ? null :    criteriaBuilder.equal(root.get("userId"), userId));
    }
}
