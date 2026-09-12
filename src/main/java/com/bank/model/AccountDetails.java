package com.bank.model;

public class AccountDetails {

    private int transactionId;
    private int accountId;
    private String accountNumber;
    private double credit;
    private double debit;
    private String transactionDate;

    public AccountDetails() {
    }

    public AccountDetails(int transactionId,
                          int accountId,
                          String accountNumber,
                          double credit,
                          double debit,
                          String transactionDate) {

        this.transactionId = transactionId;
        this.accountId = accountId;
        this.accountNumber = accountNumber;
        this.credit = credit;
        this.debit = debit;
        this.transactionDate = transactionDate;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public double getDebit() {
        return debit;
    }

    public void setDebit(double debit) {
        this.debit = debit;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }
}