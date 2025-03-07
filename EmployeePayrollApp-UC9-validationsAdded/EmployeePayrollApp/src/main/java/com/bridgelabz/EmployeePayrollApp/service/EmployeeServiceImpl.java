////For service layer
//package com.bridgelabz.EmployeePayrollApp.service;
//
//import com.bridgelabz.EmployeePayrollApp.model.Employee;
//import com.bridgelabz.EmployeePayrollApp.dto.EmployeeDTO;
//import com.bridgelabz.EmployeePayrollApp.repository.EmployeeRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class EmployeeServiceImpl implements EmployeeService {
//
//    @Autowired
//    private EmployeeRepository employeeRepository;
//
//    // Convert Employee to EmployeeDTO
//    private EmployeeDTO convertToDTO(Employee employee) {
//        return new EmployeeDTO(employee.getId(), employee.getName(), employee.getSalary(), employee.getDepartment());
//    }
//
//    @Override
//    public List<EmployeeDTO> getAllEmployees() {
//        // Get all employees and convert them to EmployeeDTO
//        return employeeRepository.findAll().stream()
//                .map(this::convertToDTO)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
//        // Create a new Employee entity from EmployeeDTO
//        Employee employee = new Employee();
//        employee.setName(employeeDTO.getName());
//        employee.setSalary(employeeDTO.getSalary());
//        employee.setDepartment(employeeDTO.getDepartment());
//
//        // Save the Employee entity to the database
//        Employee savedEmployee = employeeRepository.save(employee);
//
//        // Return the saved employee as EmployeeDTO, including the generated id
//        return new EmployeeDTO(savedEmployee.getId(), savedEmployee.getName(), savedEmployee.getSalary(), savedEmployee.getDepartment());
//    }
//
//    @Override
//    public EmployeeDTO getEmployeeById(Long id) {
//        // Fetch the Employee by id and convert it to EmployeeDTO
//        Employee employee = employeeRepository.findById(id).orElseThrow();
//        return convertToDTO(employee);
//    }
//
//    @Override
//    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
//        // Find the employee by id and update the employee details
//        Employee employee = employeeRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Employee not found"));
//
//        // Set the updated values from EmployeeDTO
//        employee.setName(employeeDTO.getName());
//        employee.setSalary(employeeDTO.getSalary());
//        employee.setDepartment(employeeDTO.getDepartment());
//
//        // Save the updated employee entity
//        Employee updatedEmployee = employeeRepository.save(employee);
//
//        // Return the updated employee as EmployeeDTO
//        return convertToDTO(updatedEmployee);
//    }
//
//    @Override
//    public void deleteEmployee(Long id) {
//        // Delete the employee by id
//        employeeRepository.deleteById(id);
//    }
//}



/// /USING LIBRARY LOMBOK:

//package com.bridgelabz.EmployeePayrollApp.service;
//
//import com.bridgelabz.EmployeePayrollApp.model.Employee;
//import com.bridgelabz.EmployeePayrollApp.dto.EmployeeDTO;
//import com.bridgelabz.EmployeePayrollApp.repository.EmployeeRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class EmployeeServiceImpl implements EmployeeService {
//
//    @Autowired
//    private EmployeeRepository employeeRepository;
//
//    // Convert Employee to EmployeeDTO
//    private EmployeeDTO convertToDTO(Employee employee) {
//        return new EmployeeDTO(employee.getId(), employee.getName(), employee.getSalary(), employee.getDepartment());
//    }
//
//    @Override
//    public List<EmployeeDTO> getAllEmployees() {
//        return employeeRepository.findAll().stream()
//                .map(this::convertToDTO)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
//        Employee employee = new Employee();
//        employee.setName(employeeDTO.getName());
//        employee.setSalary(employeeDTO.getSalary());
//        employee.setDepartment(employeeDTO.getDepartment());
//
//        Employee savedEmployee = employeeRepository.save(employee);
//
//        return new EmployeeDTO(savedEmployee.getId(), savedEmployee.getName(), savedEmployee.getSalary(), savedEmployee.getDepartment());
//    }
//
//    @Override
//    public EmployeeDTO getEmployeeById(Long id) {
//        Employee employee = employeeRepository.findById(id).orElseThrow();
//        return convertToDTO(employee);
//    }
//
//    @Override
//    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
//        Employee employee = employeeRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Employee not found"));
//
//        employee.setName(employeeDTO.getName());
//        employee.setSalary(employeeDTO.getSalary());
//        employee.setDepartment(employeeDTO.getDepartment());
//
//        Employee updatedEmployee = employeeRepository.save(employee);
//
//        return convertToDTO(updatedEmployee);
//    }
//
//    @Override
//    public void deleteEmployee(Long id) {
//        employeeRepository.deleteById(id);
//    }
//}



/// /USING LOOGER:

package com.bridgelabz.EmployeePayrollApp.service;

import com.bridgelabz.EmployeePayrollApp.model.Employee;
import com.bridgelabz.EmployeePayrollApp.dto.EmployeeDTO;
import com.bridgelabz.EmployeePayrollApp.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j  // Lombok's annotation for logger
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    private EmployeeDTO convertToDTO(Employee employee) {
        return new EmployeeDTO(employee.getId(), employee.getName(), employee.getSalary(), employee.getDepartment());
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        log.info("Fetching all employees from the database.");
        return employeeRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        log.info("Creating employee: {}", employeeDTO);
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setSalary(employeeDTO.getSalary());
        employee.setDepartment(employeeDTO.getDepartment());

        Employee savedEmployee = employeeRepository.save(employee);

        log.info("Employee created with ID: {}", savedEmployee.getId());
        return new EmployeeDTO(savedEmployee.getId(), savedEmployee.getName(), savedEmployee.getSalary(), savedEmployee.getDepartment());
    }

    @Override
    public EmployeeDTO getEmployeeById(Long id) {
        log.info("Fetching employee with ID: {}", id);
        Employee employee = employeeRepository.findById(id).orElseThrow();
        return convertToDTO(employee);
    }

    @Override
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
        log.info("Updating employee with ID: {}", id);
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        employee.setName(employeeDTO.getName());
        employee.setSalary(employeeDTO.getSalary());
        employee.setDepartment(employeeDTO.getDepartment());

        Employee updatedEmployee = employeeRepository.save(employee);

        log.info("Employee updated with ID: {}", updatedEmployee.getId());
        return convertToDTO(updatedEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {
        log.info("Deleting employee with ID: {}", id);
        employeeRepository.deleteById(id);
    }
}


////FOR VALIDATION:
//package com.bridgelabz.EmployeePayrollApp.service;
//
//import com.bridgelabz.EmployeePayrollApp.dto.EmployeeDTO;
//import com.bridgelabz.EmployeePayrollApp.model.Employee;
//import com.bridgelabz.EmployeePayrollApp.repository.EmployeeRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@Slf4j
//public class EmployeeServiceImpl implements EmployeeService {
//
//    @Autowired
//    private EmployeeRepository employeeRepository;
//
//    private EmployeeDTO convertToDTO(Employee employee) {
//        return new EmployeeDTO(employee.getId(), employee.getName(), employee.getDepartment(), employee.getSalary());
//    }
//
//    @Override
//    public List<EmployeeDTO> getAllEmployees() {
//        log.info("Fetching all employees from database.");
//        return employeeRepository.findAll().stream()
//                .map(this::convertToDTO)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
//        log.info("Creating employee: {}", employeeDTO);
//        Employee employee = new Employee();
//        employee.setName(employeeDTO.getName());
//        employee.setSalary(employeeDTO.getSalary());
//        employee.setDepartment(employeeDTO.getDepartment());
//
//        Employee savedEmployee = employeeRepository.save(employee);
//        log.info("Employee created with ID: {}", savedEmployee.getId());
//
//        return convertToDTO(savedEmployee);
//    }
//
//    @Override
//    public EmployeeDTO getEmployeeById(Long id) {
//        log.info("Fetching employee with ID: {}", id);
//        Employee employee = employeeRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Employee not found"));
//        return convertToDTO(employee);
//    }
//
//    @Override
//    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
//        log.info("Updating employee with ID: {}", id);
//        Employee employee = employeeRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Employee not found"));
//
//        employee.setName(employeeDTO.getName());
//        employee.setSalary(employeeDTO.getSalary());
//        employee.setDepartment(employeeDTO.getDepartment());
//
//        Employee updatedEmployee = employeeRepository.save(employee);
//        log.info("Employee updated with ID: {}", updatedEmployee.getId());
//
//        return convertToDTO(updatedEmployee);
//    }
//
//    @Override
//    public void deleteEmployee(Long id) {
//        log.info("Deleting employee with ID: {}", id);
//        employeeRepository.deleteById(id);
//    }
//}
