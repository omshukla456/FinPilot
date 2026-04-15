package com.om.expensemanager.ai;

import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    public String categorize(String description) {

        if (description == null) return "Other";

        description = description.toLowerCase();

        if (description.contains("pizza") || description.contains("burger") || description.contains("food")) {
            return "Food";
        }

        if (description.contains("uber") || description.contains("ola") || description.contains("bus") || description.contains("train")) {
            return "Travel";
        }

        if (description.contains("movie") || description.contains("netflix") || description.contains("game")) {
            return "Entertainment";
        }

        if (description.contains("amazon") || description.contains("flipkart") || description.contains("shopping")) {
            return "Shopping";
        }

        return "Other";
    }
}