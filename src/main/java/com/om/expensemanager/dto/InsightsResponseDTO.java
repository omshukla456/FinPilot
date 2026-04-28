package com.om.expensemanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InsightsResponseDTO {

    private Double totalExpense;
    private Double averageExpense;
    private String topCategory;
}