//package com.bridgelabz.EmployeePayrollApp.service;
//
//import com.bridgelabz.EmployeePayrollApp.dto.UserDTO;
//import com.bridgelabz.EmployeePayrollApp.model.User;
//import com.bridgelabz.EmployeePayrollApp.repository.UserRepository;
//import com.bridgelabz.EmployeePayrollApp.security.JwtUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Slf4j
//@Service
//public class UserService implements IUserService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private EmailService emailService;
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//
//    // Register User
//    @Override
//    public String registerUser(UserDTO userdto) {
//        log.info("Registering new user: {}", userdto.getEmail());
//        if (userRepository.existsByEmail(userdto.getEmail())) {
//            return "Email is already in use!";
//        }
//
//        User user = new User();
//        user.setUsername(userdto.getUsername());
//        user.setEmail(userdto.getEmail());
//        user.setPassword(passwordEncoder.encode(userdto.getPassword())); // Encrypt password
//        userRepository.save(user);
//
//        // Send welcome email
//        String subject = "Welcome to Our Platform!";
//        String body = "<h1>Hello " + userdto.getUsername() + "!</h1>"
//                + "<p>Thank you for registering on our platform.</p>";
//        emailService.sendEmail(user.getEmail(), subject, body);
//
//        log.info("User {} registered successfully.", user.getEmail());
//
//        // Clear Redis cache when a new user is registered
//        clearUserCache(user.getEmail());
//
//        return "User registered successfully!";
//    }
//
//    // Authenticate User and Generate Token
//    @Override
//    @Cacheable(value = "users", key = "#email")  // Cache user authentication
//    public String authenticateUser(String email, String password) {
//        log.info("Login attempt for email: {}", email);
//        Optional<User> userOpt = userRepository.findByEmail(email);
//
//        if (userOpt.isEmpty()) {
//            log.warn("Login failed: No user found for email: {}", email);
//            return "User not found!";
//        }
//
//        User user = userOpt.get();
//
//        if (!passwordEncoder.matches(password, user.getPassword())) {
//            log.warn("Login failed: Incorrect password for email: {}", email);
//            return "Invalid email or password!";
//        }
//
//        log.info("Login successful for user: {}", email);
//        return jwtUtil.generateToken(email);
//    }
//
//    // Forgot Password Implementation
//    @Override
//    @CacheEvict(value = "users", key = "#email")  // Remove cached user data when password is updated
//    public String forgotPassword(String email, String newPassword) {
//        log.info("Processing forgot password request for email: {}", email);
//        Optional<User> userOpt = userRepository.findByEmail(email);
//        if (userOpt.isEmpty()) {
//            log.warn("Forgot password request failed: No user found for email: {}", email);
//            return "Sorry! We cannot find the user email: " + email;
//        }
//
//        User user = userOpt.get();
//        user.setPassword(passwordEncoder.encode(newPassword));
//        userRepository.save(user);
//
//        // Send email notification
//        String subject = "Password Change Notification";
//        String content = "<h2>Hello " + user.getUsername() + ",</h2>"
//                + "<p>Your password has been changed successfully.</p>";
//        emailService.sendEmail(user.getEmail(), subject, content);
//
//        log.info("Password updated successfully for email: {}", email);
//        return "Password has been changed successfully!";
//    }
//
//    // Reset Password Implementation
//    @Override
//    @CacheEvict(value = "users", key = "#email")  // Clear cache after password reset
//    public String resetPassword(String email, String currentPassword, String newPassword) {
//        log.info("Resetting password for email: {}", email);
//        Optional<User> userOpt = userRepository.findByEmail(email);
//        if (userOpt.isEmpty()) {
//            log.warn("Password reset failed: No user found for email: {}", email);
//            return "User not found with email: " + email;
//        }
//        User user = userOpt.get();
//
//        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
//            log.warn("Password reset failed: Incorrect current password for email: {}", email);
//            return "Current password is incorrect!";
//        }
//
//        user.setPassword(passwordEncoder.encode(newPassword));
//        userRepository.save(user);
//
//        // Send email notification
//        String subject = "Password Reset Notification";
//        String content = "<h2>Hello " + user.getUsername() + ",</h2>"
//                + "<p>Your password has been reset successfully.</p>";
//        emailService.sendEmail(user.getEmail(), subject, content);
//
//        log.info("Password reset successful for email: {}", email);
//        return "Password reset successfully!";
//    }
//
//    // Clear user cache after an update
//    private void clearUserCache(String email) {
//        log.info("Clearing Redis cache for user: {}", email);
//    }
//}

package com.bridgelabz.EmployeePayrollApp.service;

import com.bridgelabz.EmployeePayrollApp.dto.UserDTO;
import com.bridgelabz.EmployeePayrollApp.model.User;
import com.bridgelabz.EmployeePayrollApp.repository.UserRepository;
import com.bridgelabz.EmployeePayrollApp.security.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private JwtUtil jwtUtil;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Register User
    @Override
    public String registerUser(UserDTO userDTO) {
        log.info("Registering new user: {}", userDTO.getEmail());

        if (userRepository.existsByEmail(userDTO.getEmail())) {
            log.warn("Registration failed: Email {} is already in use!", userDTO.getEmail());
            return "Email is already in use!";
        }

        try {
            User user = new User();
            user.setUsername(userDTO.getUsername());
            user.setEmail(userDTO.getEmail());
            user.setPassword(passwordEncoder.encode(userDTO.getPassword())); // Encrypt password
            userRepository.save(user);

            // Send welcome email
            String subject = "Welcome to Our Platform!";
            String body = "<h1>Hello " + userDTO.getUsername() + "!</h1><p>Thank you for registering.</p>";
            emailService.sendEmail(user.getEmail(), subject, body);

            log.info("User {} registered successfully.", user.getEmail());

            // Clear cache to refresh user data
            clearUserCache(user.getEmail());

            return "User registered successfully!";
        } catch (Exception e) {
            log.error("Error registering user: {}", e.getMessage(), e);
            return "Registration failed due to an internal error.";
        }
    }

    // Authenticate User and Generate JWT Token
    @Override
    @Cacheable(value = "users", key = "#email")
    public String authenticateUser(String email, String password) {
        log.info("Login attempt for email: {}", email);
        Optional<User> userOpt = userRepository.findByEmail(email);

        if (userOpt.isEmpty()) {
            log.warn("Login failed: No user found for email: {}", email);
            return "User not found!";
        }

        User user = userOpt.get();

        if (!passwordEncoder.matches(password, user.getPassword())) {
            log.warn("Login failed: Incorrect password for email: {}", email);
            return "Invalid email or password!";
        }

        try {
            String token = jwtUtil.generateToken(email);
            log.info("Login successful for user: {}", email);
            return token;
        } catch (Exception e) {
            log.error("Error generating JWT token: {}", e.getMessage(), e);
            return "Internal Server Error: Unable to generate token!";
        }
    }

    // Forgot Password Implementation
    @Override
    @CacheEvict(value = "users", key = "#email")
    public String forgotPassword(String email, String newPassword) {
        log.info("Processing forgot password request for email: {}", email);
        Optional<User> userOpt = userRepository.findByEmail(email);

        if (userOpt.isEmpty()) {
            log.warn("Forgot password request failed: No user found for email: {}", email);
            return "Sorry! We cannot find the user email: " + email;
        }

        try {
            User user = userOpt.get();
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);

            // Send email notification
            String subject = "Password Change Notification";
            String content = "<h2>Hello " + user.getUsername() + ",</h2><p>Your password has been changed successfully.</p>";
            emailService.sendEmail(user.getEmail(), subject, content);

            log.info("Password updated successfully for email: {}", email);
            return "Password has been changed successfully!";
        } catch (Exception e) {
            log.error("Error resetting password: {}", e.getMessage(), e);
            return "Password reset failed due to an internal error.";
        }
    }

    // Reset Password Implementation
    @Override
    @CacheEvict(value = "users", key = "#email")
    public String resetPassword(String email, String currentPassword, String newPassword) {
        log.info("Resetting password for email: {}", email);
        Optional<User> userOpt = userRepository.findByEmail(email);

        if (userOpt.isEmpty()) {
            log.warn("Password reset failed: No user found for email: {}", email);
            return "User not found with email: " + email;
        }

        try {
            User user = userOpt.get();

            if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
                log.warn("Password reset failed: Incorrect current password for email: {}", email);
                return "Current password is incorrect!";
            }

            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);

            // Send email notification
            String subject = "Password Reset Notification";
            String content = "<h2>Hello " + user.getUsername() + ",</h2><p>Your password has been reset successfully.</p>";
            emailService.sendEmail(user.getEmail(), subject, content);

            log.info("Password reset successful for email: {}", email);
            return "Password reset successfully!";
        } catch (Exception e) {
            log.error("Error resetting password: {}", e.getMessage(), e);
            return "Password reset failed due to an internal error.";
        }
    }

    // Clear user cache after an update
    private void clearUserCache(String email) {
        log.info("Clearing Redis cache for user: {}", email);
    }
}
