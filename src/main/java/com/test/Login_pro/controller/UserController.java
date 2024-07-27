package com.test.Login_pro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.Login_pro.entity.User;
import com.test.Login_pro.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/data")
    public ResponseEntity<User> getUserData(Authentication authentication) {
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        return ResponseEntity.ok(user);
    }
}

