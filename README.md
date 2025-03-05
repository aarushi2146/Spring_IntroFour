Employee Payroll App Setup 🧑‍💼💼<br><br>
📅 Date: 3rd March 2025<br>
📜 Title: Employee Payroll Spring Project Setup<br><br><br>

UC 1: Employee Payroll Spring Project 🖥️🚀<br><br>
Create an Employee Payroll Spring Project to cater to REST requests from the Employee Payroll UI. We will reuse the Employee Payroll Frontend UI that has already been developed. The project will make REST calls to the Employee Payroll Spring App instead of the previous JSON server. We will also test the REST API using CURL Commands.<br>

Employee Payroll Spring Project Setup 🔧:<br>
Set the Name to EmployeePayrollApp.<br>
Set the group (typically the root package) to com.bridgelabz.<br>
Set the Package Name to com.bridgelabz.employeepayrollapp.<br>
Keep other default values populated.<br>
Employee Payroll Project Dependencies 📦:<br>
Spring Web Dependency: To build RESTful Web Services.<br>
Spring Boot Dev Tools: Provides fast application restarts, live reloads, etc.<br>
Validation: For validating the REST data for POST and PUT calls.<br>
MySQL Driver: To connect to the MySQL repository.<br>
Java Persistence API (JPA): To store data in the MySQL server.<br><br><br>




-------<br><br>


UC 2: Create a Rest Controller to Demonstrate Various HTTP Methods 🌐⚙️<br><br>
Steps to follow:<br>
Set application.properties:<br>

Go to src/main/resources and open the application.properties file.<br>
Set the following values:<br>
spring.datasource.driver-class-name=com.mysql.jdbc.Driver<br>
spring.datasource.url=jdbc:mysql://localhost:3306/payroll_service<br>
spring.datasource.username=root<br>
spring.datasource.password=bridgelabz<br>
Ensure that MySQL DB is running in the background.<br>
Test the REST Calls using CURL 🔄:<br>

The objective at this stage is to establish connectivity and ensure that data is transmitted correctly in REST calls.<br>
Run the project to ensure no errors.<br>

Additional Details 📝
Technologies & Tools:<br><br>

Spring Boot: For backend development.<br>
Spring Security: For authentication.<br>
Spring Data JPA: For database interaction.<br>
JWT: For token-based authentication.<br>
MySQL Database: For data storage.<br>
🛠️ Ensure to set up the application properties properly and test using CURL to confirm your API is working!<br><br>


-------<br><br>
