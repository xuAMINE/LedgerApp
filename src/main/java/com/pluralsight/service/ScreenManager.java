package com.pluralsight.service;

import com.pluralsight.model.SearchFilter;
import com.pluralsight.model.Transaction;
import com.pluralsight.repository.CSVTransactionRepository;
import com.pluralsight.repository.TransactionRepository;
import com.pluralsight.util.TransactionBuilder;
import com.pluralsight.view.HomeView;
import com.pluralsight.view.LedgerView;

import java.math.BigDecimal;
import java.util.List;


public class ScreenManager {
    private final HomeView homeView = new HomeView();
    private final LedgerView ledgerView = new LedgerView();
    private final TransactionBuilder transactionBuilder = new TransactionBuilder();
    private final LedgerServiceImpl ledgerService = new LedgerServiceImpl(new CSVTransactionRepository());
    private final TransactionRepository transactionRepository = new CSVTransactionRepository();
    String RESET = "\u001B[0m";
    String BOLD = "\u001B[1m";
    String YELLOW = "\u001B[33m";


    public void handleHomeMenu() {
        while (true) {
            homeView.displayMenu();
            System.out.print("\nPlease select an option: ");
            String choice = homeView.getUserChoice();

            switch (choice) {
                case "D":
                    homeView.promptForDeposit();
                    String description = homeView.promptForDescription();
                    String vendor = homeView.promptForVendor();
                    BigDecimal amount = homeView.promptForAmount();
                    Transaction transaction = transactionBuilder.buildTransaction(description, vendor, amount);
                    ledgerService.addDeposit(transaction);
                    System.out.println("Your balance is: " + BOLD + YELLOW + ledgerService.getBalance() + RESET);
                    break;
                case "P":
                    homeView.promptForPayment();
                    String description1 = homeView.promptForDescription();
                    String vendor1 = homeView.promptForVendor();
                    BigDecimal amount1 = homeView.promptForAmount();
                    Transaction transaction1 = transactionBuilder.buildTransaction(description1, vendor1, amount1);
                    ledgerService.makePayment(transaction1);
                    System.out.println("Your balance is: " + ledgerService.getBalance());
                    break;
                case "L":
                    handleLedgerMenu();
                    break;
                case "X":
                    System.out.println("See you soon!");
                    return;
                default:
                    System.err.println("❗ Invalid choice. Please try again.");
            }
        }

    }

    public void handleLedgerMenu() {
        while (true) {
            ledgerView.displayLedgerMenu();
            System.out.print("\nPlease select an option: ");
            String choice = ledgerView.getUserChoice();

            switch (choice) {
                case "A":
                    List<Transaction> allTransactions = transactionRepository.findAll();
                    ledgerView.displayTransactions(allTransactions);
                    System.out.println("Your balance is: " + ledgerService.getBalance());
                    break;
                case "D":
                    List<Transaction> deposits = ledgerService.getDeposits();
                    ledgerView.displayTransactions(deposits);
                    System.out.println("Your balance is: " + ledgerService.getBalance());
                    break;
                case "P":
                    List<Transaction> payments = ledgerService.getPayments();
                    ledgerView.displayTransactions(payments);
                    System.out.println("Your balance is: " + ledgerService.getBalance());
                    break;
                case "R":
                    handleReportMenu();
                    break;
                case "H":
                    System.out.println("↩ Returning to previous menu...");
                    return; // ⬅️ Return to Home Menu
                default:
                    System.err.println("❗ Invalid choice. Please try again.");
            }
        }
    }

    public void handleReportMenu() {
        while (true) {
            ledgerView.displayReportMenu();
            System.out.print("\nPlease select an option: ");
            String choice = ledgerView.getUserChoice();

            switch (choice) {
                case "1":
                    List<Transaction> monthTransactions = ledgerService.getMonthToDateTransactions();
                    ledgerView.displayTransactions(monthTransactions);
                    break;
                case "2":
                    List<Transaction> prevMonthTransaction = ledgerService.getPreviousMonthTransactions();
                    ledgerView.displayTransactions(prevMonthTransaction);
                    break;
                case "3":
                    List<Transaction> yearTransactions = ledgerService.getYearToDateTransactions();
                    ledgerView.displayTransactions(yearTransactions);
                    break;
                case "4":
                    List<Transaction> prevYearTransaction = ledgerService.getPreviousYearTransactions();
                    ledgerView.displayTransactions(prevYearTransaction);
                    break;
                case "5":
                    String vendor = ledgerView.promptForVendorName();
                    List<Transaction> transactionsByVendor = ledgerService.getTransactionsByVendor(vendor);
                    ledgerView.displayTransactions(transactionsByVendor);
                    break;
                case "6":
                    SearchFilter searchFilter = ledgerView.promptForSearchFilter();
                    List<Transaction> filteredTransactions = ledgerService.search(searchFilter);
                    ledgerView.displayTransactions(filteredTransactions);
                    break;
                case "0":
                    System.out.println("↩ Returning to previous menu...");
                    return;
                default:
                    System.err.println("❗ Invalid choice. Please try again.");
            }
        }
    }
}
