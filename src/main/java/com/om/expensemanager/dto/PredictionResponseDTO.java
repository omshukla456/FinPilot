package com.om.expensemanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PredictionResponseDTO {

    private Double predictedNextExpense;
}