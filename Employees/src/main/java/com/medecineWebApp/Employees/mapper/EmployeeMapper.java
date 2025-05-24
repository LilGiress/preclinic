package com.medecineWebApp.Employees.mapper;

import com.medecineWebApp.Employees.dto.EmployeeDTO;
import com.medecineWebApp.Employees.models.Employee;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    EmployeeDTO employeeToEmployeeDTO(Employee employee);
    @InheritInverseConfiguration
    Employee employeeDTOToEmployee(EmployeeDTO employeeDTO);
}
