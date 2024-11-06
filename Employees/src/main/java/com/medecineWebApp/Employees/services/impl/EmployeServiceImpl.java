package com.medecineWebApp.Employees.services.impl;

import com.medecineWebApp.Employees.dto.EmployeeDTO;
import com.medecineWebApp.Employees.enums.EmployeeStatus;
import com.medecineWebApp.Employees.enums.EventType;
import com.medecineWebApp.Employees.filter.EmployeeSpecifications;
import com.medecineWebApp.Employees.mapper.EmployeeMapper;
import com.medecineWebApp.Employees.models.Employee;
import com.medecineWebApp.Employees.repository.EmployeeRepository;
import com.medecineWebApp.Employees.services.EmployeService;
import com.medecineWebApp.Employees.utilis.PublishEmployeeEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeServiceImpl implements EmployeService {
    private final EmployeeRepository employeeRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final EmployeeMapper employeeMapper;


    public EmployeServiceImpl(EmployeeRepository employeeRepository, ApplicationEventPublisher eventPublisher, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.eventPublisher = eventPublisher;
        this.employeeMapper = employeeMapper;
    }

    @Override
    @PublishEmployeeEvent(eventType=EventType.CREATED)
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setPassword(employeeDTO.getPassword());
        employee.setFirstname(employeeDTO.getFirstname());
        employee.setLastname(employeeDTO.getLastname());
        employee.setEmail(employeeDTO.getEmail());
        employee.setAttendanceRecords(employeeDTO.getAttendanceRecords());
        employee.setDepartment(employeeDTO.getDepartment());
        employee.setPosition(employeeDTO.getPosition());
        employee.setStatus(EmployeeStatus.ACTIVE);
        employee.setRoles(employeeDTO.getRoles());
        employee.setDateDebutEntreEnFonction(employeeDTO.getDateDebutEntreEnFonction());
        Employee savedEmployee = employeeRepository.save(employee);


//        EmployeeEvent event = new EmployeeEvent();
//        event.setId(savedEmployee.getId());
//        event.setPassword(savedEmployee.getPassword());
//        event.setFirstname(savedEmployee.getFirstname());
//        event.setLastname(savedEmployee.getLastname());
//        event.setEmail(savedEmployee.getEmail());
//        event.setRoles(savedEmployee.getRoles());
//        event.setDepartments(List.of(savedEmployee.getDepartment()));
//        eventPublisher.publishEvent(event);


        return employeeMapper.employeeToEmployeeDTO(savedEmployee);
    }

    @Override
    public Optional<EmployeeDTO> getEmployee(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            return Optional.ofNullable(employeeMapper.employeeToEmployeeDTO(employee.get()));
        }
        return Optional.empty();
    }

    @Override
    public Page<EmployeeDTO> findEmployeesByFirstNameOrLastNameAndEmployeIdAndRole(String firstName, String lastName, int employeId, String role, int page, int size) {
        Specification<Employee> specification = Specification.where(
                EmployeeSpecifications.firstNameLike(firstName)
                        .or(EmployeeSpecifications.lastNameLike(lastName))
                        .and(EmployeeSpecifications.hasEmployeId(employeId))
                        .and(EmployeeSpecifications.hasRole(role))
        ) ;
        Pageable pageable = PageRequest.of(page, size);
        return employeeRepository.findAll(specification, pageable).map(employeeMapper::employeeToEmployeeDTO);
    }

    @Override
    @PublishEmployeeEvent(eventType=EventType.UPDATED)
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO,Long UserId) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isPresent()) {
            Employee employeeToUpdate = employeeOptional.get();
            employeeToUpdate.setDepartment(employeeDTO.getDepartment());
            employeeToUpdate.setFirstname(employeeDTO.getFirstname());
            employeeToUpdate.setLastname(employeeDTO.getLastname());
            employeeToUpdate.setRoles(employeeDTO.getRoles());
            employeeToUpdate.setDateDebutEntreEnFonction(employeeDTO.getDateDebutEntreEnFonction());
            employeeToUpdate.setPosition(employeeDTO.getPosition());
            employeeToUpdate.setEmail(employeeDTO.getEmail());
            employeeToUpdate.setStatus(employeeDTO.getStatus());
            employeeToUpdate.setAttendanceRecords(employeeDTO.getAttendanceRecords());
            Employee updatedEmployee = employeeRepository.save(employeeToUpdate);


//            EmployeeUpdatedEvent event = new EmployeeUpdatedEvent();
//            event.setId(updatedEmployee.getId());
//            event.setUserId(UserId);
//            event.setPassword(updatedEmployee.getPassword());
//            event.setFirstname(updatedEmployee.getFirstname());
//            event.setLastname(updatedEmployee.getLastname());
//            event.setEmail(updatedEmployee.getEmail());
//            event.setRoles(updatedEmployee.getRoles());
//            event.setDepartments(List.of(updatedEmployee.getDepartment()));
//            event.setUsername(updatedEmployee.getUsername());
//            eventPublisher.publishEvent(event);


            return employeeMapper.employeeToEmployeeDTO(updatedEmployee);
        }
        throw new RuntimeException("Employee not found") ;
    }

    @Override
    @PublishEmployeeEvent(eventType=EventType.DELETED)
    public void deleteEmployee(Long id,Long UserId) {
       // Employee employee = employeeRepository.findById(id).orElseThrow();

//        EmployeeDeletedEvent event = new EmployeeDeletedEvent();
//        event.setId(employee.getId());
//        event.setUserId(UserId);
//        eventPublisher.publishEvent(event);

    employeeRepository.deleteById(id);
    }
}
