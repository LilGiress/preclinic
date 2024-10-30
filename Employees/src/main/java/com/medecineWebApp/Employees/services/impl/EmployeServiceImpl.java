package com.medecineWebApp.Employees.services.impl;

import com.medecineWebApp.Employees.config.EmployeeClient;
import com.medecineWebApp.Employees.filter.EmployeeSpecifications;
import com.medecineWebApp.Employees.models.Employee;
import com.medecineWebApp.Employees.repository.EmployeeRepository;
import com.medecineWebApp.Employees.services.EmployeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class EmployeServiceImpl implements EmployeService {
    private final EmployeeRepository employeeRepository;


    public EmployeServiceImpl(EmployeeRepository employeeRepository, EmployeeClient employeeClient) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Optional<Employee> getEmployee(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee;
    }

    @Override
    public Page<Employee> findEmployeesByFirstNameOrLastNameAndEmployeIdAndRole(String firstName, String lastName, int employeId, String role, int page, int size) {
        Specification<Employee> specification = Specification.where(
                EmployeeSpecifications.firstNameLike(firstName)
                        .or(EmployeeSpecifications.lastNameLike(lastName))
                        .and(EmployeeSpecifications.hasEmployeId(employeId))
                        .and(EmployeeSpecifications.hasRole(role))
        ) ;
        Pageable pageable = PageRequest.of(page, size);
        return employeeRepository.findAll(specification, pageable);
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isPresent()) {
            Employee employeeToUpdate = employeeOptional.get();
            employeeToUpdate.setDepartment(employee.getDepartment());
            employeeToUpdate.setFirstname(employee.getFirstname());
            employeeToUpdate.setLastname(employee.getLastname());
            employeeToUpdate.setRole(employee.getRole());
            employeeToUpdate.setDateDebutEntreEnFonction(employee.getDateDebutEntreEnFonction());
            employeeToUpdate.setPosition(employee.getPosition());
            employeeToUpdate.setEmail(employee.getEmail());
            employeeToUpdate.setDateOfCreation(employee.getDateOfCreation());
            employeeToUpdate.setStatus(employee.getStatus());
            employeeToUpdate.setLastModifiedDate(employee.getLastModifiedDate());
            employeeToUpdate.setAttendanceRecords(employee.getAttendanceRecords());
            return employeeRepository.save(employeeToUpdate);
        }
        throw new RuntimeException("Employee not found") ;
    }

    @Override
    public void deleteEmployee(Long id) {
    employeeRepository.deleteById(id);
    }
}
