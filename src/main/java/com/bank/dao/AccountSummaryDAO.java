package com.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountSummaryDAO {

    public double getBalance(Connection connection,
                             int accountId)
            throws SQLException {

        String sql =
                "SELECT available_balance " +
                        "FROM account_summary " +
                        "WHERE account_id = ? " +
                        "FOR UPDATE";

        try (PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, accountId);

            try (ResultSet resultSet =
                         statement.executeQuery()) {

                if (resultSet.next()) {

                    return resultSet.getDouble(
                            "available_balance"
                    );
                }

                throw new SQLException(
                        "Account not found: " + accountId
                );
            }
        }
    }


    public void updateBalance(Connection connection,
                              int accountId,
                              double newBalance)
            throws SQLException {

        String sql =
                "UPDATE account_summary " +
                        "SET available_balance = ? " +
                        "WHERE account_id = ?";

        try (PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setDouble(1, newBalance);
            statement.setInt(2, accountId);

            int rowsUpdated =
                    statement.executeUpdate();

            if (rowsUpdated != 1) {

                throw new SQLException(
                        "Balance update failed for account: "
                                + accountId
                );
            }
        }
    }
}