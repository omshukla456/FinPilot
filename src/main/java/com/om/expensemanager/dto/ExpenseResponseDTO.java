package com.om.expensemanager.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ExpenseResponseDTO {

    private Long id;
    private Double amount;
    private String description;
    private String category;
    private LocalDate date;

    private String userName;
    private String userEmail;
}