package com.bank.model;

public class AccountSummary {

    private int accountId;
    private double availableBalance;

    public AccountSummary() {
    }

    public AccountSummary(int accountId,
                          double availableBalance) {

        this.accountId = accountId;
        this.availableBalance = availableBalance;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public double getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(double availableBalance) {
        this.availableBalance = availableBalance;
    }

    @Override
    public String toString() {

        return "AccountSummary{" +
                "accountId=" + accountId +
                ", availableBalance=" +
                availableBalance +
                '}';
    }
}