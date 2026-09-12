package com.bank;

import com.bank.model.AccountTransactionView;
import com.bank.service.AccountService;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        AccountService service =
                new AccountService();


        while (true) {

            System.out.println();
//            System.out.println(
//                    "=============================================="
//            );
//            System.out.println(
//                    "          BANK TRANSACTION SYSTEM"
//            );
//            System.out.println(
//                    "=============================================="
//            );

            System.out.println("1. Credit");
            System.out.println("2. Debit");
            System.out.println("3. Transaction Report");
            System.out.println("4. Exit");

            System.out.print(
                    "Enter your choice: "
            );

            int choice = scanner.nextInt();


            // =========================
            // EXIT
            // =========================

            if (choice == 4) {

                System.out.println();
                System.out.println(
                        "Thank you for using Bank Transaction System!"
                );

                break;
            }


            // =========================
            // TRANSACTION REPORT
            // =========================

            if (choice == 3) {

                try {

                    List<AccountTransactionView>
                            transactions =
                            service.getTransactionReport();

                    System.out.println();

//                    System.out.println(
//                            "=============================================================================================================="
//                    );

                    System.out.println(
                            "                                      TRANSACTION REPORT"
                    );

                    System.out.println(
                            "                                  INNER JOIN RESULT"
                    );

//                    System.out.println(
//                            "=============================================================================================================="
//                    );


                    if (transactions.isEmpty()) {

                        System.out.println(
                                "No transactions found."
                        );

                    } else {

                        // TABLE HEADER

                        System.out.printf(
                                "%-8s %-12s %-16s %-14s %-14s %-24s %-16s%n",
                                "Txn ID",
                                "Account ID",
                                "Account Number",
                                "Credit",
                                "Debit",
                                "Transaction Date",
                                "Balance"
                        );

//                        System.out.println(
//                                "--------------------------------------------------------------------------------------------------------------"
//                        );


                        // TABLE DATA

                        for (AccountTransactionView transaction
                                : transactions) {

                            System.out.printf(
                                    "%-8d %-12d %-16s %-14.2f %-14.2f %-24s %-16.2f%n",

                                    transaction.getTransactionId(),

                                    transaction.getAccountId(),

                                    transaction.getAccountNumber(),

                                    transaction.getCredit(),

                                    transaction.getDebit(),

                                    transaction.getTransactionDate(),

                                    transaction.getAvailableBalance()
                            );
                        }


                        //System.out.println(
                               // "--------------------------------------------------------------------------------------------------------------"
                        //);
                    }


                    //System.out.println(
                      //      "=============================================================================================================="
                    //);

                } catch (Exception e) {

                    System.out.println();

                    System.out.println(
                            "Unable to display transaction report."
                    );

                    System.out.println(
                            e.getMessage()
                    );
                }

                continue;
            }


            // =========================
            // INVALID CHOICE
            // =========================

            if (choice != 1 && choice != 2) {

                System.out.println(
                        "Invalid choice!"
                );

                continue;
            }


            // =========================
            // ACCOUNT ID
            // =========================

            System.out.print(
                    "Enter Account ID: "
            );

            int accountId =
                    scanner.nextInt();


            // =========================
            // ACCOUNT NUMBER
            // =========================

            System.out.print(
                    "Enter Account Number: "
            );

            String accountNumber =
                    scanner.next();


            // =========================
            // AMOUNT
            // =========================

            System.out.print(
                    "Enter Amount: "
            );

            double amount =
                    scanner.nextDouble();


            try {

                // =========================
                // CREDIT
                // =========================

                if (choice == 1) {

                    double newBalance =
                            service.credit(
                                    accountId,
                                    accountNumber,
                                    amount
                            );

                    System.out.println();

                    System.out.println(
                            "Credit successful!"
                    );

                    System.out.printf(
                            "New Balance: ₹%.2f%n",
                            newBalance
                    );
                }


                // =========================
                // DEBIT
                // =========================

                else {

                    double newBalance =
                            service.debit(
                                    accountId,
                                    accountNumber,
                                    amount
                            );

                    System.out.println();

                    System.out.println(
                            "Debit successful!"
                    );

                    System.out.printf(
                            "New Balance: ₹%.2f%n",
                            newBalance
                    );
                }

            } catch (Exception e) {

                System.out.println();

                System.out.println(
                        "Transaction failed!"
                );

                System.out.println(
                        e.getMessage()
                );
            }
        }


        scanner.close();
    }
}