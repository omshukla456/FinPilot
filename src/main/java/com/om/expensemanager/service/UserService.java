package com.om.expensemanager.service;

import com.om.expensemanager.model.user;
import com.om.expensemanager.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public user register(user user) {
        return userRepository.save(user);
    }

    public Optional<user> login(String email, String password) {
        Optional<user> user = userRepository.findByEmail(email);

        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return user;
        }

        return Optional.empty();
    }
}