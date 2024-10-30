package com.medecineWebApp.Employees.services;

import com.medecineWebApp.Employees.models.Employee;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface EmployeService {
    Employee createEmployee(Employee employee);
    Optional<Employee> getEmployee(Long id);
    Page<Employee> findEmployeesByFirstNameOrLastNameAndEmployeIdAndRole(String firstName,String lastName,int employeId,String role, int page, int size);
    Employee updateEmployee(Long id,Employee employee);
    void deleteEmployee(Long id);
}
