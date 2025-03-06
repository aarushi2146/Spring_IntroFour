//package com.bridgelabz.EmployeePayrollApp.dto;
//
//public class EmployeeDTO {
//
//    private Long id;  // Add id field here
//    private String name;
//    private double salary;
//
//    // Default constructor
//    public EmployeeDTO() {
//    }
//
//    // Constructor with parameters
//    public EmployeeDTO(Long id, String name, double salary) {
//        this.id = id;
//        this.name = name;
//        this.salary = salary;
//    }
//
//    // Getters and Setters
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public double getSalary() {
//        return salary;
//    }
//
//    public void setSalary(double salary) {
//        this.salary = salary;
//    }
//}
package com.bridgelabz.EmployeePayrollApp.dto;

public class EmployeeDTO {

    private Long id;
    private String name;
    private double salary;

    // Default constructor (required by Spring for deserialization)
    public EmployeeDTO() {
    }

    // Constructor with parameters for name and salary
    public EmployeeDTO(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    // Constructor with parameters for id, name, and salary (optional, if you need id)
    public EmployeeDTO(Long id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
