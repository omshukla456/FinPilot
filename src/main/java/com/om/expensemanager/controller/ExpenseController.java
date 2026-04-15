package com.om.expensemanager.controller;

import com.om.expensemanager.dto.ExpenseRequestDTO;
import com.om.expensemanager.dto.ExpenseResponseDTO;
import com.om.expensemanager.dto.PredictionResponseDTO;
import com.om.expensemanager.model.User;
import com.om.expensemanager.service.ExpenseService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
@CrossOrigin
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    // ➕ ADD EXPENSE (FIX)

    // 📊 GET ALL
    @GetMapping("/all")
    public List<ExpenseResponseDTO> getAllExpenses() {
        return expenseService.getAllExpenses();
    }
    @GetMapping("/predict/{userId}")
    public PredictionResponseDTO getPrediction(@PathVariable Long userId) {
        return expenseService.getPrediction(userId);
    }
    @GetMapping("/insights/{userId}")
    public List<String> getInsights(@PathVariable Long userId) {
        return expenseService.getInsights(userId);
    }
    @PostMapping("/add")
    public ExpenseResponseDTO addExpense(
            @RequestBody ExpenseRequestDTO dto,
            HttpServletRequest request
    ) {
        User user = (User) request.getAttribute("user");
        return expenseService.addExpenseWithUser(dto, user);
    }

}