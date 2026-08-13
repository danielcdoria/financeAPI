package com.example.financeAPI.dtos.transactionDtos;

import com.example.financeAPI.models.Transaction;

public class TransactionResponseDto {
    private Long id;
    private String title;
    private double amount;
    private Transaction.Type type;
    private String category;
    private String date;

    public TransactionResponseDto(){

    }

    public TransactionResponseDto(Long id,
                                  String title,
                                  double amount,
                                  Transaction.Type type,
                                  String category,
                                  String date){

        this.id = id;
        this.title = title;
        this.amount = amount;
        this.type = type;
        this.category = category;
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public Transaction.Type getType() {
        return type;
    }

    public Long getId() {
        return id;
    }
}
