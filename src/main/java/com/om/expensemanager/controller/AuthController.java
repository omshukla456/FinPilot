package com.om.expensemanager.controller;

import com.om.expensemanager.model.user;
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
    public user register(@RequestBody user user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody user user) {
        Optional<user> existingUser = userService.login(user.getEmail(), user.getPassword());

        if (existingUser.isPresent()) {
            return "Login Successful";
        } else {
            return "Invalid Credentials";
        }
    }
}