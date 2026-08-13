package com.example.financeAPI.dtos.transactionDtos;

import com.example.financeAPI.models.Transaction;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class TransactionRequestDto {
    @NotBlank(message = "Title cannot be blank.")
    private String title;
    @Positive(message = "The amount must be greater than 0.")
    private double amount;
    @NotNull(message = "Type cannot be null.")
    private Transaction.Type type;
    @NotBlank(message = "Category cannot be blank.")
    private String category;
    @NotBlank(message = "Date cannot be blank.")
    private String date;

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    public Transaction.Type getType() {
        return type;
    }

    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }
}
