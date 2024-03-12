package com.bankfoo.models;

import java.util.Date;

public class Transaction {
    private String transactionId;
    private String accountNumber; // Associated account
    private double amount;
    private Date transactionDate;
    private String type; // e.g., DEPOSIT, WITHDRAWAL, TRANSFER
    private String status; // e.g., PENDING, COMPLETED

    // Constructor
    public Transaction(String transactionId, String accountNumber, double amount, Date transactionDate, String type, String status) {
        this.transactionId = transactionId;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.type = type;
        this.status = status;
    }

    // Getters and Setters
    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
