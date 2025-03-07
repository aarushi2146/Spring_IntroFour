package com.bridgelabz.EmployeePayrollApp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
@SpringBootApplication
public class EmployeePayrollAppApplication {

	public static void main(String[] args) {
		// Get the application context
		ApplicationContext context = SpringApplication.run(EmployeePayrollAppApplication.class, args);

		// Log the environment
		log.info("Employee Payroll App started in {} Environment",
				context.getEnvironment().getProperty("spring.profiles.active"));
	}
}
