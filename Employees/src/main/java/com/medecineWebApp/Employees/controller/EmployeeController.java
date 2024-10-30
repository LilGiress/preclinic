package com.medecineWebApp.Employees.controller;

import com.medecineWebApp.Employees.config.EmployeeClient;
import com.medecineWebApp.Employees.models.Employee;
import com.medecineWebApp.Employees.models.Patient;
import com.medecineWebApp.Employees.services.EmployeService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {
    private final EmployeService employeService;
    private final EmployeeClient employeeClient;

    public EmployeeController(EmployeService employeService, EmployeeClient employeeClient) {
        this.employeService = employeService;
        this.employeeClient = employeeClient;
    }
    @PostMapping("/save")
    public ResponseEntity<Employee> createEmployee(Employee employee) {
        return ResponseEntity.ok(employeService.createEmployee(employee));

    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Employee>> getEmployeeById( @PathVariable Long id) {
        return ResponseEntity.ok(employeService.getEmployee(id));
    }
    @GetMapping("/search")
    public ResponseEntity<Page<Employee>> searchEmployees(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam int employeId,
            @RequestParam String role,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(employeService.findEmployeesByFirstNameOrLastNameAndEmployeIdAndRole(firstName, lastName, employeId, role, page, size));

    }
    @PutMapping("/update")
    public ResponseEntity<Employee> updateEmploye(
            @RequestParam Long employeId,
            @RequestBody Employee employee
    ){
return ResponseEntity.ok(employeService.updateEmployee(employeId,employee));
    }

    @DeleteMapping("/delete")
    public void deleteEmployee(@RequestParam Long employeId) {
        employeService.deleteEmployee(employeId);
    }

    @GetMapping("/patient/{id}")
    public Patient getPatient(@PathVariable Long id) {
        return employeeClient.getPatient(id);
    }
}
