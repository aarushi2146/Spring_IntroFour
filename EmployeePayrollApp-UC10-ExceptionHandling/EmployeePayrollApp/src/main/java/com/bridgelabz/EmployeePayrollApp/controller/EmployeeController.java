//package com.bridgelabz.EmployeePayrollApp.controller;
//
//import com.bridgelabz.EmployeePayrollApp.model.Employee;
//import com.bridgelabz.EmployeePayrollApp.repository.EmployeeRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
////@RestController
////@RequestMapping("/api/employees")
////public class EmployeeController {
////
////    @Autowired
////    private EmployeeRepository employeeRepository;
////
////    // GET: Get all employees
////    @GetMapping
////    public List<Employee> getAllEmployees() {
////        return employeeRepository.findAll();
////    }
////
////    // POST: Create a new employee
////    @PostMapping
////    public Employee createEmployee(@RequestBody Employee employee) {
////        return employeeRepository.save(employee);
////    }
////
////    // GET: Get employee by ID
////    @GetMapping("/{id}")
////    public Optional<Employee> getEmployeeById(@PathVariable Long id) {
////        return employeeRepository.findById(id);
////    }
////
////    // PUT: Update employee
////    @PutMapping("/{id}")
////    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
////        Employee employee = employeeRepository.findById(id).orElseThrow();
////        employee.setName(employeeDetails.getName());
////        employee.setDepartment(employeeDetails.getDepartment());
////        employee.setSalary(employeeDetails.getSalary());
////        return employeeRepository.save(employee);
////    }
////
////    // DELETE: Delete employee by ID
////    @DeleteMapping("/{id}")
////    public void deleteEmployee(@PathVariable Long id) {
////        employeeRepository.deleteById(id);
////    }
////}
//
//
//
//
////CURL Calls for Testing
//@RestController
//@RequestMapping("employeepayrollservice")  // Update the base path to match the cURL commands
//public class EmployeeController {
//
//    @Autowired
//    private EmployeeRepository employeeRepository;
//
//    // GET: Get all employees (matches curl localhost:8080/employeepayrollservice/)
//    @GetMapping("/")
//    public List<Employee> getAllEmployees() {
//        return employeeRepository.findAll();
//    }
//
//    // POST: Create a new employee (matches curl -X POST localhost:8080/employeepayrollservice/create)
//    @PostMapping("/create")
//    public Employee createEmployee(@RequestBody Employee employee) {
//        return employeeRepository.save(employee);
//    }
//
//    // GET: Get employee by ID (matches curl localhost:8080/employeepayrollservice/get/1)
//    @GetMapping("/get/{id}")
//    public Optional<Employee> getEmployeeById(@PathVariable Long id) {
//        return employeeRepository.findById(id);
//    }
//
//    // PUT: Update employee (matches curl -X PUT localhost:8080/employeepayrollservice/update)
//    @PutMapping("/update")
//    public Employee updateEmployee(@RequestBody Employee employeeDetails) {
//        Employee employee = employeeRepository.findById(employeeDetails.getId())
//                .orElseThrow();
//        employee.setName(employeeDetails.getName());
//        employee.setDepartment(employeeDetails.getDepartment());
//        employee.setSalary(employeeDetails.getSalary());
//        return employeeRepository.save(employee);
//    }
//
//    // DELETE: Delete employee by ID (matches curl -X DELETE localhost:8080/employeepayrollservice/delete/1)
//    @DeleteMapping("/delete/{id}")
//    public void deleteEmployee(@PathVariable Long id) {
//        employeeRepository.deleteById(id);
//    }
//}







// FOR DTO:
//package com.bridgelabz.EmployeePayrollApp.controller;
//
//import com.bridgelabz.EmployeePayrollApp.model.Employee;
//import com.bridgelabz.EmployeePayrollApp.dto.EmployeeDTO;
//import com.bridgelabz.EmployeePayrollApp.repository.EmployeeRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@RestController
//@RequestMapping("employeepayrollservice")
//public class EmployeeController {
//
//    @Autowired
//    private EmployeeRepository employeeRepository;
//
//    // Convert Employee to EmployeeDTO
//    private EmployeeDTO convertToDTO(Employee employee) {
//        return new EmployeeDTO(employee.getName(), employee.getSalary());
//    }
//
//    // GET: Get all employees (matches curl localhost:8080/employeepayrollservice/)
//    @GetMapping("/")
//    public List<EmployeeDTO> getAllEmployees() {
//        return employeeRepository.findAll().stream()
//                .map(this::convertToDTO)
//                .collect(Collectors.toList());
//    }
//
//    // POST: Create a new employee (matches curl -X POST localhost:8080/employeepayrollservice/create)
//    @PostMapping("/create")
//    public EmployeeDTO createEmployee(@RequestBody EmployeeDTO employeeDTO) {
//        Employee employee = new Employee();
//        employee.setName(employeeDTO.getName());
//        employee.setSalary(employeeDTO.getSalary());
//        Employee savedEmployee = employeeRepository.save(employee);
//        return convertToDTO(savedEmployee);
//    }
//
//    // GET: Get employee by ID (matches curl localhost:8080/employeepayrollservice/get/1)
//    @GetMapping("/get/{id}")
//    public EmployeeDTO getEmployeeById(@PathVariable Long id) {
//        Employee employee = employeeRepository.findById(id).orElseThrow();
//        return convertToDTO(employee);
//    }
//
//    // PUT: Update employee (matches curl -X PUT localhost:8080/employeepayrollservice/update)
//    @PutMapping("/update")
//    public EmployeeDTO updateEmployee(@RequestBody EmployeeDTO employeeDTO) {
//        Employee employee = employeeRepository.findById(employeeDTO.getId())
//                .orElseThrow();
//        employee.setName(employeeDTO.getName());
//        employee.setSalary(employeeDTO.getSalary());
//        Employee updatedEmployee = employeeRepository.save(employee);
//        return convertToDTO(updatedEmployee);
//    }
//
//    // DELETE: Delete employee by ID (matches curl -X DELETE localhost:8080/employeepayrollservice/delete/1)
//    @DeleteMapping("/delete/{id}")
//    public void deleteEmployee(@PathVariable Long id) {
//        employeeRepository.deleteById(id);
//    }
//}



//FOR SERVICE:
//package com.bridgelabz.EmployeePayrollApp.controller;
//
//import com.bridgelabz.EmployeePayrollApp.dto.EmployeeDTO;
//import com.bridgelabz.EmployeePayrollApp.service.EmployeeService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/employeepayrollservice")
//public class EmployeeController {
//
//    @Autowired
//    private EmployeeService employeeService;
//
//    @GetMapping("/employees")
//    public List<EmployeeDTO> getAllEmployees() {
//        return employeeService.getAllEmployees();
//    }
//
//    @GetMapping("/employee/{id}")
//    public EmployeeDTO getEmployeeById(@PathVariable Long id) {
//        return employeeService.getEmployeeById(id);
//    }
//
//    @PostMapping("/employee")
//    public EmployeeDTO createEmployee(@RequestBody EmployeeDTO employeeDTO) {
//        return employeeService.createEmployee(employeeDTO);
//    }
//
//    @PutMapping("/update/{id}")  // Here we specify {id} in the path
//    public EmployeeDTO updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {
//        return employeeService.updateEmployee(id, employeeDTO);  // Pass the id and DTO to service layer
//    }
//
//    @DeleteMapping("/employee/{id}")
//    public void deleteEmployee(@PathVariable Long id) {
//        employeeService.deleteEmployee(id);
//    }
//}



////FOR LOOGER:
//package com.bridgelabz.EmployeePayrollApp.controller;
//
//import com.bridgelabz.EmployeePayrollApp.dto.EmployeeDTO;
//import com.bridgelabz.EmployeePayrollApp.service.EmployeeService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/employeepayrollservice")
//@Slf4j  // Lombok's annotation for logger
//public class EmployeeController {
//
//    @Autowired
//    private EmployeeService employeeService;
//
//    @GetMapping("/employees")
//    public List<EmployeeDTO> getAllEmployees() {
//        log.info("Fetching all employees.");
//        return employeeService.getAllEmployees();
//    }
//
//    @GetMapping("/employee/{id}")
//    public EmployeeDTO getEmployeeById(@PathVariable Long id) {
//        log.info("Fetching employee with ID: {}", id);
//        return employeeService.getEmployeeById(id);
//    }
//
//    @PostMapping("/employee")
//    public EmployeeDTO createEmployee(@RequestBody EmployeeDTO employeeDTO) {
//        log.info("Creating new employee: {}", employeeDTO);
//        return employeeService.createEmployee(employeeDTO);
//    }
//
//    @PutMapping("/update/{id}")
//    public EmployeeDTO updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {
//        log.info("Updating employee with ID: {}, New Data: {}", id, employeeDTO);
//        return employeeService.updateEmployee(id, employeeDTO);
//    }
//
//    @DeleteMapping("/employee/{id}")
//    public void deleteEmployee(@PathVariable Long id) {
//        log.info("Deleting employee with ID: {}", id);
//        employeeService.deleteEmployee(id);
//    }
//}



////FOR VALIDATION:
package com.bridgelabz.EmployeePayrollApp.controller;

import com.bridgelabz.EmployeePayrollApp.dto.EmployeeDTO;
import com.bridgelabz.EmployeePayrollApp.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import java.util.List;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // ✅ Get All Employees
    @GetMapping("/")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        List<EmployeeDTO> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    // ✅ Get Employee By ID with Exception Handling
    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable Long id) {
        try {
            EmployeeDTO employee = employeeService.getEmployeeById(id);
            return ResponseEntity.ok(employee);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found with ID: " + id);
        }
    }

    // ✅ Add Employee with Validation Errors Handled
    @PostMapping("/add")
    public ResponseEntity<?> addEmployee(@Valid @RequestBody EmployeeDTO employeeDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(getValidationErrors(result));
        }
        EmployeeDTO savedEmployee = employeeService.createEmployee(employeeDTO);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    // ✅ Update Employee with Exception Handling
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeDTO employeeDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(getValidationErrors(result));
        }
        try {
            EmployeeDTO updatedEmployee = employeeService.updateEmployee(id, employeeDTO);
            return ResponseEntity.ok(updatedEmployee);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found with ID: " + id);
        }
    }

    // ✅ Delete Employee with Exception Handling
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        try {
            employeeService.deleteEmployee(id);
            return ResponseEntity.ok("Employee deleted successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found with ID: " + id);
        }
    }

    // 🔹 Utility Method to Handle Validation Errors
    private Map<String, String> getValidationErrors(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : result.getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return errors;
    }
}
//@RestController
//@RequestMapping("/employees")
//@Validated
//public class EmployeeController {
//
//    @Autowired
//    private EmployeeService employeeService;
//
//    @GetMapping("/")
//    public List<EmployeeDTO> getAllEmployees() {
//        return employeeService.getAllEmployees();
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
//        EmployeeDTO employee = employeeService.getEmployeeById(id);
//        return employee != null ? ResponseEntity.ok(employee) : ResponseEntity.notFound().build();
//    }
//
//    @PostMapping("/add")
//    public ResponseEntity<EmployeeDTO> addEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
//        EmployeeDTO savedEmployee = employeeService.createEmployee(employeeDTO);
//        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
//    }
//
//    @PutMapping("/update/{id}")
//    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeDTO employeeDTO) {
//        EmployeeDTO updatedEmployee = employeeService.updateEmployee(id, employeeDTO);
//        return ResponseEntity.ok(updatedEmployee);
//    }
//
//    @DeleteMapping("/employee/{id}")
//    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
//        employeeService.deleteEmployee(id);
//        return ResponseEntity.ok("Employee deleted successfully!");
//    }
//}


////FOR EXCEPTION:
//package com.bridgelabz.EmployeePayrollApp.controller;
//
//import com.bridgelabz.EmployeePayrollApp.dto.EmployeeDTO;
//import com.bridgelabz.EmployeePayrollApp.service.EmployeeService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//import jakarta.validation.Valid;
//import java.util.List;
//
//@RestController
//@RequestMapping("/employeepayrollservice")
//@Slf4j
//@Validated
//public class EmployeeController {
//
//    @Autowired
//    private EmployeeService employeeService;
//
//    @GetMapping("/employees")
//    public List<EmployeeDTO> getAllEmployees() {
//        log.info("Fetching all employees.");
//        return employeeService.getAllEmployees();
//    }
//
//    @GetMapping("/employee/{id}")
//    public EmployeeDTO getEmployeeById(@PathVariable Long id) {
//        log.info("Fetching employee with ID: {}", id);
//        return employeeService.getEmployeeById(id);
//    }
//
//    @PostMapping("/employee")
//    public EmployeeDTO createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
//        log.info("Creating new employee: {}", employeeDTO);
//        return employeeService.createEmployee(employeeDTO);
//    }
//
//    @PutMapping("/update/{id}")
//    public EmployeeDTO updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeDTO employeeDTO) {
//        log.info("Updating employee with ID: {}, New Data: {}", id, employeeDTO);
//        return employeeService.updateEmployee(id, employeeDTO);
//    }
//
//    @DeleteMapping("/employee/{id}")
//    public void deleteEmployee(@PathVariable Long id) {
//        log.info("Deleting employee with ID: {}", id);
//        employeeService.deleteEmployee(id);
//    }
//}
