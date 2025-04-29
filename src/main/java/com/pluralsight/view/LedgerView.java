package com.pluralsight.view;

import com.pluralsight.model.Transaction;

import java.math.RoundingMode;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class LedgerView {
    private final Scanner sc = new Scanner(System.in);
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void displayLedgerMenu() {
        System.out.println("\n==================================");
        System.out.println("📜 Ledger Menu");
        System.out.println("==================================");
        System.out.println("A) View All Transactions");
        System.out.println("D) View Deposits Only");
        System.out.println("P) View Payments Only");
        System.out.println("R) Run Reports");
        System.out.println("H) Return Home");
        System.out.println("==================================");
    }

    public String getUserChoice() {
        return sc.nextLine().trim().toUpperCase();
    }

    public void displayTransactions(List<Transaction> transactions) {
        System.out.println("\n==================================");
        System.out.printf("%-20s %-30s %-20s %-10s\n", "Date/Time", "Description", "Vendor", "Amount");
        System.out.println("---------------------------------------------------------------------------------------");

        for (Transaction t : transactions) {
            System.out.printf(
                    "%-20s %-30s %-20s %-10s\n",
                    t.getDateTime().format(formatter),
                    t.getDescription(),
                    t.getVendor(),
                    t.getAmount().setScale(2, RoundingMode.HALF_UP)
            );
        }

        System.out.println("==================================\n");
    }

    public void displayReportMenu() {
        System.out.println("\n==================================");
        System.out.println("📈 Reports Menu");
        System.out.println("==================================");
        System.out.println("1) Month To Date");
        System.out.println("2) Previous Month");
        System.out.println("3) Year To Date");
        System.out.println("4) Previous Year");
        System.out.println("5) Search by Vendor");
        System.out.println("6) Custom Search (optional bonus)");
        System.out.println("0) Back to Ledger Menu");
        System.out.println("==================================");
    }

    public String promptForVendorName() {
        System.out.print("Enter Vendor Name to Search: ");
        return sc.nextLine();
    }
}