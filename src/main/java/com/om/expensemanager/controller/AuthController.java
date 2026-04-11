package com.om.expensemanager.controller;

import com.om.expensemanager.model.User;
import com.om.expensemanager.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        Optional<User> existingUser = userService.login(user.getEmail(), user.getPassword());

        if (existingUser.isPresent()) {
            return "Login Successful";
        } else {
            return "Invalid Credentials";
        }
    }
}