package com.om.expensemanager.service;

import com.om.expensemanager.dto.ExpenseRequestDTO;
import com.om.expensemanager.dto.ExpenseResponseDTO;
import com.om.expensemanager.model.Expense;
import com.om.expensemanager.model.User;
import com.om.expensemanager.repository.ExpenseRepository;
import com.om.expensemanager.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;

    public ExpenseService(ExpenseRepository expenseRepository, UserRepository userRepository) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
    }

    // ➕ Add Expense
    public ExpenseResponseDTO addExpense(ExpenseRequestDTO dto) {

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Expense expense = new Expense();
        expense.setAmount(dto.getAmount());
        expense.setDescription(dto.getDescription());
        expense.setCategory(dto.getCategory());
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

    // ❌ Delete Expense
    public void deleteExpense(Long id) {

        if (!expenseRepository.existsById(id)) {
            throw new RuntimeException("Expense not found with ID: " + id);
        }

        expenseRepository.deleteById(id);
    }
}