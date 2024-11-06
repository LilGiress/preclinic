package com.medecineWebApp.Employees.services;

import com.medecineWebApp.Employees.dto.EmployeeDTO;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface EmployeService {
    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);
    Optional<EmployeeDTO> getEmployee(Long id);
    Page<EmployeeDTO> findEmployeesByFirstNameOrLastNameAndEmployeIdAndRole(String firstName,String lastName,int employeId,String role, int page, int size);
    EmployeeDTO updateEmployee(Long id,EmployeeDTO employeeDTO,Long UserId);
    void deleteEmployee(Long id,Long UserId);
}
