package com.medecineWebApp.Employees.controller;

import com.medecineWebApp.Employees.config.EmployeeClient;
import com.medecineWebApp.Employees.dto.EmployeeDTO;
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
    public ResponseEntity<EmployeeDTO> createEmployee(EmployeeDTO employee) {
        return ResponseEntity.ok(employeService.createEmployee(employee));

    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<EmployeeDTO>> getEmployeeById( @PathVariable Long id) {
        return ResponseEntity.ok(employeService.getEmployee(id));
    }
    @GetMapping("/search")
    public ResponseEntity<Page<EmployeeDTO>> searchEmployees(
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
    public ResponseEntity<EmployeeDTO> updateEmploye(
            @RequestParam Long employeId,
            @RequestBody EmployeeDTO employee,
            @RequestParam Long userId
    ){
return ResponseEntity.ok(employeService.updateEmployee(employeId,employee,userId));
    }

    @DeleteMapping("/delete")
    public void deleteEmployee(@RequestParam Long employeId, @RequestParam Long userId) {
        employeService.deleteEmployee(employeId,userId);
    }

    @GetMapping("/patient/{id}")
    public Patient getPatient(@PathVariable Long id) {
        return employeeClient.getPatient(id);
    }
}
