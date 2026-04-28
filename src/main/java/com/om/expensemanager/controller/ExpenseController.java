package com.om.expensemanager.controller;

import com.om.expensemanager.dto.*;
import com.om.expensemanager.service.ExpenseService;
import jakarta.validation.Valid;
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

    @PostMapping("/add")
    public ExpenseResponseDTO addExpense(@Valid @RequestBody ExpenseRequestDTO dto) {
        return expenseService.addExpense(dto);
    }

    @GetMapping("/all")
    public List<ExpenseResponseDTO> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    @GetMapping("/insights")
    public InsightsResponseDTO getInsights() {
        return expenseService.getInsights();
    }

    @GetMapping("/predict")
    public PredictionResponseDTO predictExpense() {
        return expenseService.predictExpense();
    }
}