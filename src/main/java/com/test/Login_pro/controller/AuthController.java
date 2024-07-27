package com.test.Login_pro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.test.Login_pro.entity.User;
import com.test.Login_pro.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = userService.findByUsername(loginRequest.getUsername());

        // Check the role of the authenticated user
        if ("ADMIN".equals(user.getRole())) {
            // Fetch all users if the authenticated user is an ADMIN
            List<User> allUsers = userService.findAllUsers(); // Replace with your method to fetch all users
            return ResponseEntity.ok(allUsers);
        } else {
            // Return the single user's details for non-admin roles
            return ResponseEntity.ok(user);
        }
    }

 
}
