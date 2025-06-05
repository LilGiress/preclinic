package com.medecineWebApp.Notification.filter;

import com.medecineWebApp.Notification.models.Notification;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class NotificationSpecifications {

    public static Specification<Notification> withFilters(NotificationFilter filter) {
        return Specification
                .where(byUserName(filter.getUserName()))
                .and(byDateBetween(filter.getStartDate(), filter.getEndDate()))
                .and(byType(filter.getType()))
                .and(byStatus(filter.getStatus()));
    }

    public static Specification<Notification> byUserName(String userName) {
        return (root, query, cb) -> {
            if (userName == null || userName.trim().isEmpty()) return null;
            return cb.like(cb.lower(root.get("userName")), "%" + userName.toLowerCase() + "%");
        };
    }

    public static Specification<Notification> byDateBetween(LocalDate start, LocalDate end) {
        return (root, query, cb) -> {
            if (start != null && end != null) {
                return cb.between(root.get("createdAt"), start.atStartOfDay(), end.atTime(23, 59, 59));
            } else if (start != null) {
                return cb.greaterThanOrEqualTo(root.get("createdAt"), start.atStartOfDay());
            } else if (end != null) {
                return cb.lessThanOrEqualTo(root.get("createdAt"), end.atTime(23, 59, 59));
            }
            return null;
        };
    }

    public static Specification<Notification> byType(String type) {
        return (root, query, cb) -> {
            if (type == null || type.trim().isEmpty()) return null;
            return cb.equal(root.get("type"), type);
        };
    }

    public static Specification<Notification> byStatus(String status) {
        return (root, query, cb) -> {
            if (status == null || status.trim().isEmpty()) return null;
            return cb.equal(root.get("status"), status);
        };
    }
}
