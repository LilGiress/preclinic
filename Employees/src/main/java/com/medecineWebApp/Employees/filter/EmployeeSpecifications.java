package com.medecineWebApp.Employees.filter;

import com.medecineWebApp.Employees.models.Employee;
import org.springframework.data.jpa.domain.Specification;

public class EmployeeSpecifications {
    public static Specification<Employee> firstNameLike(String search) {
        return (((root, query, criteriaBuilder) ->
                search == null ? null : criteriaBuilder.like(root.get("firstName"), "%" + search + "%")));
    }
    public static Specification<Employee> lastNameLike(String search) {
        return (((root, query, criteriaBuilder) ->
                search == null ? null : criteriaBuilder.like(root.get("lastName"), "%" + search + "%")));
    }
    public static Specification<Employee> hasRole(String role) {
        return (((root, query, criteriaBuilder) ->
             role == null ? null :  criteriaBuilder.equal(root.get("role"), role)));
    }

    public static Specification<Employee> hasEmployeId(int employeId) {
        return (((root, query, criteriaBuilder) ->
                    employeId <0 ? null : criteriaBuilder.equal(root.get("employeId"), employeId) ));
    }
}
