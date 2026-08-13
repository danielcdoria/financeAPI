package com.example.financeAPI.models;

import jakarta.persistence.*;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private double amount;
    @Enumerated(EnumType.STRING)
    private Type type;
    private String category;
    private String date;

    @ManyToOne
    private User user;

    public enum Type{
        INCOME, EXPENSE
    }

    public Transaction(){

    }

    public Transaction(String title,
                       double amount,
                       Type type,
                       String category,
                       String date){
        this.title = title;
        this.amount = amount;
        this.type = type;
        this.category = category;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public Type getType() {
        return type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
