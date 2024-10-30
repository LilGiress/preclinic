package com.medecineWebApp.Employees.filter;

import com.medecineWebApp.Employees.models.Attendance;
import com.medecineWebApp.Employees.models.Employee;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class AttendanceSpecifications {
    public static Specification<Attendance> attendanceDate(String date) {
        return ((root, query, criteriaBuilder) ->
            date == null ? null :    criteriaBuilder.equal(root.get("date"), date));
    }
    public static Specification<Attendance> hasYear(int year) {
        return (Root<Attendance> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            return builder.equal(builder.function("year", Integer.class, root.get("date")), year);
        };
    }

    public static Specification<Attendance> hasMonth(int month) {
        return (Root<Attendance> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            return builder.equal(builder.function("month", Integer.class, root.get("date")), month);
        };
    }

    public static Specification<Attendance> hasEmployeId(Employee employeeId) {
        return ((root, query, criteriaBuilder) ->
             employeeId == null ? null :   criteriaBuilder.equal(root.get("employee"), employeeId));
    }
}
