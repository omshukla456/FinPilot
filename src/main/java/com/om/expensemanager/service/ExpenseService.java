package com.om.expensemanager.service;

import com.om.expensemanager.model.Expense;
import com.om.expensemanager.model.user;
import com.om.expensemanager.repository.ExpenseRepository;
import com.om.expensemanager.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;

    // Constructor Injection (BEST PRACTICE)
    public ExpenseService(ExpenseRepository expenseRepository, UserRepository userRepository) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
    }

    // ➕ Add Expense
    public Expense addExpense(Expense expense) {

        // Extract user ID from request
        if (expense.getUser() == null || expense.getUser().getId() == null) {
            throw new RuntimeException("User ID must be provided");
        }

        Long userId = expense.getUser().getId();

        // Fetch full user from DB
        user user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        // Set full user object
        expense.setUser(user);

        // Save expense
        return expenseRepository.save(expense);
    }

    // 📊 Get All Expenses
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    // 📊 Get Expenses by User (VERY IMPORTANT 🔥)
    public List<Expense> getExpensesByUser(Long userId) {

        // Check if user exists
        userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return expenseRepository.findAll()
                .stream()
                .filter(expense -> expense.getUser().getId().equals(userId))
                .toList();
    }

    // ❌ Delete Expense
    public void deleteExpense(Long id) {

        if (!expenseRepository.existsById(id)) {
            throw new RuntimeException("Expense not found with ID: " + id);
        }

        expenseRepository.deleteById(id);
    }
}