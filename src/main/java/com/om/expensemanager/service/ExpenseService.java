package com.om.expensemanager.service;

import com.om.expensemanager.dto.*;
import com.om.expensemanager.model.Expense;
import com.om.expensemanager.model.User;
import com.om.expensemanager.repository.ExpenseRepository;
import com.om.expensemanager.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;

    public ExpenseService(ExpenseRepository expenseRepository, UserRepository userRepository) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
    }

    // 🔐 Get logged-in user
    private User getLoggedInUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // 🤖 AUTO CATEGORY DETECTION
    private String detectCategory(String description) {

        if (description == null) return "Other";

        String desc = description.toLowerCase();

        if (desc.contains("pizza") || desc.contains("burger") || desc.contains("food") || desc.contains("restaurant")) {
            return "Food";
        }

        if (desc.contains("uber") || desc.contains("ola") || desc.contains("bus") || desc.contains("metro")) {
            return "Travel";
        }

        if (desc.contains("amazon") || desc.contains("shopping") || desc.contains("flipkart")) {
            return "Shopping";
        }

        if (desc.contains("movie") || desc.contains("netflix") || desc.contains("game")) {
            return "Entertainment";
        }

        if (desc.contains("electricity") || desc.contains("bill") || desc.contains("rent")) {
            return "Bills";
        }

        return "Other";
    }

    // ➕ Add Expense (WITH AUTO CATEGORY)
    public ExpenseResponseDTO addExpense(ExpenseRequestDTO dto) {

        User user = getLoggedInUser();

        Expense expense = new Expense();
        expense.setAmount(dto.getAmount());
        expense.setDescription(dto.getDescription());
        expense.setDate(dto.getDate());

        // 🔥 AUTO CATEGORY
        String autoCategory = detectCategory(dto.getDescription());
        expense.setCategory(autoCategory);

        expense.setUser(user);

        Expense saved = expenseRepository.save(expense);

        return mapToDTO(saved);
    }

    // 📊 Get All Expenses
    public List<ExpenseResponseDTO> getAllExpenses() {

        User user = getLoggedInUser();

        return expenseRepository.findByUserId(user.getId())
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    // 📊 Insights API
    public InsightsResponseDTO getInsights() {

        User user = getLoggedInUser();

        List<Expense> expenses = expenseRepository.findByUserId(user.getId());

        if (expenses.isEmpty()) {
            return new InsightsResponseDTO(0.0, 0.0, "No Data");
        }

        double total = expenses.stream()
                .mapToDouble(Expense::getAmount)
                .sum();

        double avg = total / expenses.size();

        String topCategory = expenses.stream()
                .filter(e -> e.getCategory() != null)
                .collect(Collectors.groupingBy(Expense::getCategory, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("Unknown");

        return new InsightsResponseDTO(total, avg, topCategory);
    }

    // 🔮 Prediction API
    public PredictionResponseDTO predictExpense() {

        User user = getLoggedInUser();

        List<Expense> expenses = expenseRepository.findByUserId(user.getId());

        if (expenses.isEmpty()) {
            return new PredictionResponseDTO(0.0);
        }

        double avg = expenses.stream()
                .mapToDouble(Expense::getAmount)
                .average()
                .orElse(0.0);

        return new PredictionResponseDTO(avg);
    }

    // 🔁 Mapper
    private ExpenseResponseDTO mapToDTO(Expense e) {

        ExpenseResponseDTO dto = new ExpenseResponseDTO();

        dto.setId(e.getId());
        dto.setAmount(e.getAmount());
        dto.setDescription(e.getDescription());
        dto.setCategory(e.getCategory());
        dto.setDate(e.getDate());
        dto.setUserName(e.getUser().getName());
        dto.setUserEmail(e.getUser().getEmail());

        return dto;
    }
}