package com.pluralsight.service;

import com.pluralsight.model.Transaction;
import com.pluralsight.repository.CSVTransactionRepository;
import com.pluralsight.repository.TransactionRepository;
import com.pluralsight.util.TransactionBuilder;
import com.pluralsight.view.HomeView;
import com.pluralsight.view.LedgerView;

import java.math.BigDecimal;

public class ScreenManager {
    private final HomeView homeView = new HomeView();
    private final LedgerView ledgerView = new LedgerView();
    private final TransactionBuilder transactionBuilder = new TransactionBuilder();
    private final LedgerServiceImpl service = new LedgerServiceImpl(new CSVTransactionRepository());


    public void callUserChoice() {
        System.out.print("\nPlease select an option: ");
        String choice = homeView.getUserChoice();
        switch (choice) {
            case "D":
                homeView.promptForDeposit();
                String description = homeView.promptForDescription();
                String vendor = homeView.promptForVendor();
                BigDecimal amount = new BigDecimal(homeView.promptForAmount());
                Transaction transaction = transactionBuilder.buildTransaction(description, vendor, amount);
                service.addDeposit(transaction);
                break;
            case "P":
                homeView.promptForPayment();
                break;
            case "L":
                ledgerView.displayLedgerMenu();
                break;
            case "X":
                System.out.println("See you soon!");
                System.exit(0);
            default:
                System.err.println("Invalid choice!, please try again!");
                callUserChoice();
        }
    }


}
