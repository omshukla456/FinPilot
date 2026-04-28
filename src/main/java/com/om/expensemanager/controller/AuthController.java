package com.om.expensemanager.controller;

import com.om.expensemanager.model.User;
import com.om.expensemanager.repository.UserRepository;
import com.om.expensemanager.security.JwtUtil;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    // 🔐 REGISTER
    @PostMapping("/register")
    public String register(@RequestBody User user) {

        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser.isPresent()) {
            throw new RuntimeException("User already exists");
        }

        userRepository.save(user);

        return "User registered successfully";
    }

    // 🔑 LOGIN
    @PostMapping("/login")
    public String login(@RequestBody User loginUser) {

        Optional<User> user = userRepository.findByEmail(loginUser.getEmail());

        if (user.isPresent() && user.get().getPassword().equals(loginUser.getPassword())) {

            return jwtUtil.generateToken(user.get().getEmail());
        }

        throw new RuntimeException("Invalid credentials");
    }
}