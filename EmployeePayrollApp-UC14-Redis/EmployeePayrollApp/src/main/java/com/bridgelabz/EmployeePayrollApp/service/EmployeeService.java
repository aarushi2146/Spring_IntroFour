//package com.bridgelabz.EmployeePayrollApp.service;
//
//import com.bridgelabz.EmployeePayrollApp.model.Employee;
//import com.bridgelabz.EmployeePayrollApp.dto.EmployeeDTO;
//
//import java.util.List;
//import java.util.Optional;
//
//public interface EmployeeService {
//
//    List<EmployeeDTO> getAllEmployees();
//
//    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);
//
//    EmployeeDTO getEmployeeById(Long id);
//
//    EmployeeDTO updateEmployee(Long id ,EmployeeDTO employeeDTO);
//
//    void deleteEmployee(Long id);
//}

package com.bridgelabz.EmployeePayrollApp.service;

import com.bridgelabz.EmployeePayrollApp.dto.EmployeeDTO;
import com.bridgelabz.EmployeePayrollApp.model.Employee;
import com.bridgelabz.EmployeePayrollApp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EmployeeService implements IEmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Convert Employee to EmployeeDTO
    private EmployeeDTO convertToDTO(Employee employee) {
        return new EmployeeDTO(employee.getId(), employee.getName(), employee.getDepartment(), employee.getSalary());
    }

    @Override
    @Cacheable(value = "allEmployees")
    public List<EmployeeDTO> getAllEmployees() {
        log.info("Fetching all employees from the database.");
        return employeeRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @CacheEvict(value = {"employee", "allEmployees"}, allEntries = true)
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setSalary(employeeDTO.getSalary());
        employee.setDepartment(employeeDTO.getDepartment());

        Employee savedEmployee = employeeRepository.save(employee);
        log.info("Created new employee with ID: {}", savedEmployee.getId());

        return new EmployeeDTO(savedEmployee.getId(), savedEmployee.getName(), savedEmployee.getDepartment(), savedEmployee.getSalary());
    }

    @Override
    @Cacheable(value = "employee", key = "#id")
    public EmployeeDTO getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow();
        log.info("Employee found: {}", employee);
        return convertToDTO(employee);
    }

    @Override
    @CacheEvict(value = {"employees", "allEmployees"}, allEntries = true)
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        employee.setName(employeeDTO.getName());
        employee.setSalary(employeeDTO.getSalary());
        employee.setDepartment(employeeDTO.getDepartment());

        Employee updatedEmployee = employeeRepository.save(employee);
        log.info("Employee updated successfully: {}", employee);
        return convertToDTO(updatedEmployee);
    }

    @Override
    @CacheEvict(value = {"employees", "allEmployees"}, key = "#id")
    public void deleteEmployee(Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            log.info("Employee with ID {} deleted successfully.", id);
        } else {
            log.warn("Attempted to delete non-existing employee with ID: {}", id);
        }
    }
}