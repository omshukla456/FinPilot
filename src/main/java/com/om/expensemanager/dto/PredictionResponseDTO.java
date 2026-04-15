package com.om.expensemanager.dto;

import lombok.Data;

@Data
public class PredictionResponseDTO {

    private double averageDailyExpense;
    private double predictedWeeklyExpense;
}