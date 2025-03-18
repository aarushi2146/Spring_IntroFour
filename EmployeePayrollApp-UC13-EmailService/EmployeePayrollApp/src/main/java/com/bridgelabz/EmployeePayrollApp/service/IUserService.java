package com.bridgelabz.EmployeePayrollApp.service;



import com.bridgelabz.EmployeePayrollApp.dto.UserDTO;

public interface IUserService {
    public String registerUser(UserDTO userdto);
    public String authenticateUser(String email, String password);
    public String forgotPassword(String email, String newPassword);
    public String resetPassword(String email, String currentPassword, String newPassword);
}