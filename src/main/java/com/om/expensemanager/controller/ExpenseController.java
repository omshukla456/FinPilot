package com.om.expensemanager.controller;

import com.om.expensemanager.dto.ExpenseRequestDTO;
import com.om.expensemanager.dto.ExpenseResponseDTO;
import com.om.expensemanager.model.Expense;
import com.om.expensemanager.service.ExpenseService;
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



    @GetMapping("/all")
    public List<ExpenseResponseDTO> getAllExpenses() {
        return expenseService.getAllExpenses();
    }
}