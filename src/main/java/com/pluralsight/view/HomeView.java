package com.pluralsight.view;

import com.pluralsight.model.Transaction;

import java.math.BigDecimal;
import java.util.Scanner;

public class HomeView {
    private final Scanner sc = new Scanner(System.in);
    private final LedgerView ledgerView = new LedgerView();

    public void displayMenu() {
        System.out.println("\n==================================");
        System.out.println("🏦 Accounting Ledger - Home Menu");
        System.out.println("==================================");
        System.out.println("D) Add Deposit");
        System.out.println("P) Make Payment (Debit)");
        System.out.println("L) View Ledger");
        System.out.println("X) Exit");
        System.out.println("==================================");
    }

    public String getUserChoice() {
        return sc.nextLine().trim().toUpperCase();
    }

    public void promptForDeposit() {
        System.out.println("\nEnter deposit information");
    }

    public void promptForPayment() {
        System.out.println("\n# Enter payment information");
    }

    public String promptForDescription() {
        System.out.print("Enter Description: ");
        return sc.nextLine();
    }

    public String promptForVendor() {
        System.out.print("Enter Vendor: ");
        return sc.nextLine();
    }

    public BigDecimal promptForAmount() {
        while (true) {
            System.out.print("Enter Amount: ");
            String input = sc.nextLine().trim();

            try {
                return new BigDecimal(input);
            } catch (NumberFormatException e) {
                System.err.println("❌ Invalid amount. Please enter a valid number.");
                System.out.println();

            }
        }
    }




}
