package com.bank.service;

import com.bank.dao.AccountDetailsDAO;
import com.bank.dao.AccountSummaryDAO;
import com.bank.model.AccountTransactionView;
import com.bank.util.DBConnection;

import java.sql.Connection;
import java.util.List;

public class AccountService {

    private final AccountSummaryDAO accountSummaryDAO;
    private final AccountDetailsDAO accountDetailsDAO;


    public AccountService() {

        accountSummaryDAO =
                new AccountSummaryDAO();

        accountDetailsDAO =
                new AccountDetailsDAO();
    }


    // =========================
    // CREDIT
    // =========================

    public double credit(int accountId,
                         String accountNumber,
                         double amount) {

        validateAmount(amount);

        try (Connection connection =
                     DBConnection.getConnection()) {

            connection.setAutoCommit(false);

            try {

                double currentBalance =
                        accountSummaryDAO.getBalance(
                                connection,
                                accountId
                        );

                double newBalance =
                        currentBalance + amount;

                accountDetailsDAO.insertCredit(
                        connection,
                        accountId,
                        accountNumber,
                        amount
                );

                accountSummaryDAO.updateBalance(
                        connection,
                        accountId,
                        newBalance
                );

                connection.commit();

                return newBalance;

            } catch (Exception e) {

                connection.rollback();

                throw e;
            }

        } catch (Exception e) {

            throw new RuntimeException(
                    "Credit failed: "
                            + e.getMessage(),
                    e
            );
        }
    }


    // =========================
    // DEBIT
    // =========================

    public double debit(int accountId,
                        String accountNumber,
                        double amount) {

        validateAmount(amount);

        try (Connection connection =
                     DBConnection.getConnection()) {

            connection.setAutoCommit(false);

            try {

                double currentBalance =
                        accountSummaryDAO.getBalance(
                                connection,
                                accountId
                        );

                if (amount > currentBalance) {

                    throw new IllegalArgumentException(
                            "Insufficient balance. " +
                                    "Available balance: "
                                    + currentBalance
                    );
                }

                double newBalance =
                        currentBalance - amount;

                accountDetailsDAO.insertDebit(
                        connection,
                        accountId,
                        accountNumber,
                        amount
                );

                accountSummaryDAO.updateBalance(
                        connection,
                        accountId,
                        newBalance
                );

                connection.commit();

                return newBalance;

            } catch (Exception e) {

                connection.rollback();

                throw e;
            }

        } catch (Exception e) {

            throw new RuntimeException(
                    "Debit failed: "
                            + e.getMessage(),
                    e
            );
        }
    }


    // =========================
    // INNER JOIN REPORT
    // =========================

    public List<AccountTransactionView>
    getTransactionReport() {

        try {

            return accountDetailsDAO
                    .getJoinedTransactions();

        } catch (Exception e) {

            throw new RuntimeException(
                    "Unable to get transaction report: "
                            + e.getMessage(),
                    e
            );
        }
    }


    // =========================
    // VALIDATE AMOUNT
    // =========================

    private void validateAmount(double amount) {

        if (amount <= 0) {

            throw new IllegalArgumentException(
                    "Amount must be greater than zero"
            );
        }
    }
}