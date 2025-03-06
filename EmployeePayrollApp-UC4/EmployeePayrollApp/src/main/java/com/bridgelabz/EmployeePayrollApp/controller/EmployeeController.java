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
package com.bridgelabz.EmployeePayrollApp.controller;

import com.bridgelabz.EmployeePayrollApp.dto.EmployeeDTO;
import com.bridgelabz.EmployeePayrollApp.model.Employee;
import com.bridgelabz.EmployeePayrollApp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employeepayrollservice")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // GET: Get all employees
    @GetMapping("/")
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    // POST: Create a new employee
    @PostMapping("/create")
    public EmployeeDTO createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return employeeService.createEmployee(employeeDTO);
    }

    // GET: Get employee by ID
    @GetMapping("/get/{id}")
    public EmployeeDTO getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    // PUT: Update employee
    @PutMapping("/update")
    public EmployeeDTO updateEmployee(@PathVariable Long id ,@RequestBody EmployeeDTO employeeDTO) {

        return employeeService.updateEmployee(id ,employeeDTO);
    }

    // DELETE: Delete employee by ID
    @DeleteMapping("/delete/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }
}
