package com.om.expensemanager.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ExpenseRequestDTO {

    @NotNull
    private Double amount;

    @NotNull
    private String description;

    private String category;

    @NotNull
    private LocalDate date;

    @NotNull
    private Long userId;
}