package com.bridgelabz.EmployeePayrollApp.service;

import com.bridgelabz.EmployeePayrollApp.model.Employee;
import com.bridgelabz.EmployeePayrollApp.dto.EmployeeDTO;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<EmployeeDTO> getAllEmployees();

    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);

    EmployeeDTO getEmployeeById(Long id);

    EmployeeDTO updateEmployee(Long id ,EmployeeDTO employeeDTO);

    void deleteEmployee(Long id);
}
