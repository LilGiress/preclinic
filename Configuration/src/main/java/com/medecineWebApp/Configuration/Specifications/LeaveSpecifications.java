package com.medecineWebApp.Configuration.Specifications;

import com.medecineWebApp.Configuration.enums.LeaveStatus;
import com.medecineWebApp.Configuration.models.Leaves;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class LeaveSpecifications {
    public static Specification<Leaves> hasEmployeeId(Long employeeId) {
        return (root, query, cb) ->
                employeeId == null ? null :
                        cb.equal(root.get("employeeId"), employeeId);
    }

    public static Specification<Leaves> hasStatus(LeaveStatus status) {
        return (root, query, cb) ->
                status == null ? null :
                        cb.equal(root.get("status"), status);
    }

    public static Specification<Leaves> startsAfter(LocalDate startDate) {
        return (root, query, cb) ->
                startDate == null ? null :
                        cb.greaterThanOrEqualTo(root.get("startDate"), startDate);
    }

    public static Specification<Leaves> endsBefore(LocalDate endDate) {
        return (root, query, cb) ->
                endDate == null ? null :
                        cb.lessThanOrEqualTo(root.get("endDate"), endDate);
    }

    public static Specification<Leaves> hasLeaveType(Long leaveTypeId) {
        return (root, query, cb) ->
                leaveTypeId == null ? null :
                        cb.equal(root.get("leaveType").get("id"), leaveTypeId);
    }
}
