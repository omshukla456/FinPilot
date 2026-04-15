package com.om.expensemanager.service;

import com.om.expensemanager.dto.ExpenseRequestDTO;
import com.om.expensemanager.dto.ExpenseResponseDTO;
import com.om.expensemanager.dto.PredictionResponseDTO;
import com.om.expensemanager.model.Expense;
import com.om.expensemanager.model.User;
import com.om.expensemanager.repository.ExpenseRepository;
import com.om.expensemanager.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.om.expensemanager.ai.CategoryService;

import java.util.List;
import java.util.*;

@Service
public class ExpenseService {
    private final CategoryService categoryService;
    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;

    public ExpenseService(ExpenseRepository expenseRepository,
                          UserRepository userRepository,
                          CategoryService categoryService) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
        this.categoryService = categoryService;
    }

    // ➕ Add Expense
    public ExpenseResponseDTO addExpense(ExpenseRequestDTO dto) {

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Expense expense = new Expense();
        expense.setAmount(dto.getAmount());
        expense.setDescription(dto.getDescription());
        String category = categoryService.categorize(dto.getDescription());
        expense.setCategory(category);
        expense.setDate(dto.getDate());
        expense.setUser(user);

        Expense savedExpense = expenseRepository.save(expense);

        return mapToResponseDTO(savedExpense);
    }

    // 📊 Get All Expenses
    public List<ExpenseResponseDTO> getAllExpenses() {
        return expenseRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    // 🔁 Mapping Method
    private ExpenseResponseDTO mapToResponseDTO(Expense expense) {

        ExpenseResponseDTO dto = new ExpenseResponseDTO();

        dto.setId(expense.getId());
        dto.setAmount(expense.getAmount());
        dto.setDescription(expense.getDescription());
        dto.setCategory(expense.getCategory());
        dto.setDate(expense.getDate());

        dto.setUserName(expense.getUser().getName());
        dto.setUserEmail(expense.getUser().getEmail());

        return dto;
    }
    public PredictionResponseDTO getPrediction(Long userId) {

        List<Expense> expenses = expenseRepository.findAll()
                .stream()
                .filter(expense -> expense.getUser().getId().equals(userId))
                .toList();

        if (expenses.isEmpty()) {
            throw new RuntimeException("No expenses found for user");
        }

        double total = expenses.stream()
                .mapToDouble(Expense::getAmount)
                .sum();

        long days = expenses.stream()
                .map(Expense::getDate)
                .distinct()
                .count();

        double average = total / days;
        double weeklyPrediction = average * 7;

        PredictionResponseDTO dto = new PredictionResponseDTO();
        dto.setAverageDailyExpense(average);
        dto.setPredictedWeeklyExpense(weeklyPrediction);

        return dto;
    }

    public List<String> getInsights(Long userId) {

        List<Expense> expenses = expenseRepository.findAll()
                .stream()
                .filter(e -> e.getUser().getId().equals(userId))
                .toList();

        if (expenses.isEmpty()) {
            throw new RuntimeException("No expenses found");
        }

        List<String> insights = new ArrayList<>();

        // 🔥 1. Highest spending category
        Map<String, Double> categoryTotals = new HashMap<>();

        for (Expense e : expenses) {
            categoryTotals.put(
                    e.getCategory(),
                    categoryTotals.getOrDefault(e.getCategory(), 0.0) + e.getAmount()
            );
        }

        String topCategory = Collections.max(categoryTotals.entrySet(),
                Map.Entry.comparingByValue()).getKey();

        insights.add("Your highest spending is on " + topCategory);

        // 🔥 2. Total spending insight
        double total = expenses.stream()
                .mapToDouble(Expense::getAmount)
                .sum();

        if (total > 5000) {
            insights.add("You are spending quite a lot this month");
        } else {
            insights.add("Your spending is under control");
        }

        // 🔥 3. Travel warning
        if (categoryTotals.getOrDefault("Travel", 0.0) > 2000) {
            insights.add("You are spending heavily on Travel");
        }

        return insights;
    }
    public ExpenseResponseDTO addExpenseWithUser(ExpenseRequestDTO dto, User user) {

        Expense expense = new Expense();
        expense.setAmount(dto.getAmount());
        expense.setDescription(dto.getDescription());
        expense.setDate(dto.getDate());

        // 🔥 AI category
        String category = categoryService.categorize(dto.getDescription());
        expense.setCategory(category);

        expense.setUser(user);

        Expense saved = expenseRepository.save(expense);

        return mapToResponseDTO(saved);
    }

    // ❌ Delete Expense
    public void deleteExpense(Long id) {

        if (!expenseRepository.existsById(id)) {
            throw new RuntimeException("Expense not found with ID: " + id);
        }

        expenseRepository.deleteById(id);
    }
}