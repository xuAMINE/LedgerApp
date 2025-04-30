package com.pluralsight.view;

import com.pluralsight.model.SearchFilter;
import com.pluralsight.model.Transaction;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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

    public SearchFilter promptForSearchFilter() {
        SearchFilter searchFilter = new SearchFilter();

        System.out.print("\nStart Date (yyyy-MM-dd) [optional]: ");
        String start = sc.nextLine();
        try {
            if (!start.isBlank())
                searchFilter.setStartDate(LocalDate.parse(start));
        } catch (DateTimeParseException e) {
            System.err.println("❌ Invalid date format. Use yyyy-MM-dd.");
            return promptForSearchFilter();
        }

        System.out.print("End Date (yyyy-MM-dd) [optional]: ");
        String end = sc.nextLine();
        try {
            if (!end.isBlank())
                searchFilter.setEndDate(LocalDate.parse(end));
        } catch (DateTimeParseException e) {
            System.err.println ("❌ Invalid date format. Use yyyy-MM-dd.");
            return promptForSearchFilter();
        }

        System.out.print("Description [optional]: ");
        String description = sc.nextLine();
        if (!description.isBlank())
            searchFilter.setDescription(description);

        System.out.print("Vendor [optional]: ");
        String vendor = sc.nextLine();
        if (!vendor.isBlank())
            searchFilter.setVendor(vendor);

        System.out.print("Amount (e.g. 100.00) [optional]: ");
        String amountStr = sc.nextLine();
        try {
            if (!amountStr.isBlank())
                searchFilter.setAmount(new BigDecimal(amountStr));
        } catch (NumberFormatException e) {
            System.err.println("❌ Invalid amount format. Please enter a valid number.");
            return promptForSearchFilter();
        }


        return searchFilter;
    }
}