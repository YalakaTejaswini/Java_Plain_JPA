package com.bank.dao;

import com.bank.model.AccountTransactionView;
import com.bank.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDetailsDAO {

    // =========================
    // INSERT CREDIT
    // =========================

    public void insertCredit(Connection connection,
                             int accountId,
                             String accountNumber,
                             double amount)
            throws SQLException {

        String sql =
                "INSERT INTO account_details " +
                        "(account_id, account_number, credit, debit) " +
                        "VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, accountId);
            statement.setString(2, accountNumber);
            statement.setDouble(3, amount);
            statement.setDouble(4, 0);

            statement.executeUpdate();
        }
    }


    // =========================
    // INSERT DEBIT
    // =========================

    public void insertDebit(Connection connection,
                            int accountId,
                            String accountNumber,
                            double amount)
            throws SQLException {

        String sql =
                "INSERT INTO account_details " +
                        "(account_id, account_number, credit, debit) " +
                        "VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, accountId);
            statement.setString(2, accountNumber);
            statement.setDouble(3, 0);
            statement.setDouble(4, amount);

            statement.executeUpdate();
        }
    }


    // =========================
    // INNER JOIN
    // =========================

    public List<AccountTransactionView>
    getJoinedTransactions()
            throws SQLException {

        List<AccountTransactionView> transactions =
                new ArrayList<>();

        String sql =
                "SELECT " +
                        "d.transaction_id, " +
                        "d.account_id, " +
                        "d.account_number, " +
                        "d.credit, " +
                        "d.debit, " +
                        "d.transaction_date, " +
                        "s.available_balance " +
                        "FROM account_details d " +
                        "INNER JOIN account_summary s " +
                        "ON d.account_id = s.account_id " +
                        "ORDER BY d.transaction_id";

        try (Connection connection =
                     DBConnection.getConnection();

             PreparedStatement statement =
                     connection.prepareStatement(sql);

             ResultSet resultSet =
                     statement.executeQuery()) {

            while (resultSet.next()) {

                AccountTransactionView transaction =
                        new AccountTransactionView();

                transaction.setTransactionId(
                        resultSet.getInt(
                                "transaction_id"
                        )
                );

                transaction.setAccountId(
                        resultSet.getInt(
                                "account_id"
                        )
                );

                transaction.setAccountNumber(
                        resultSet.getString(
                                "account_number"
                        )
                );

                transaction.setCredit(
                        resultSet.getDouble(
                                "credit"
                        )
                );

                transaction.setDebit(
                        resultSet.getDouble(
                                "debit"
                        )
                );

                transaction.setTransactionDate(
                        resultSet.getTimestamp(
                                "transaction_date"
                        ).toString()
                );

                transaction.setAvailableBalance(
                        resultSet.getDouble(
                                "available_balance"
                        )
                );

                transactions.add(transaction);
            }
        }

        return transactions;
    }
}