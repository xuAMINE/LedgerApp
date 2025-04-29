package com.pluralsight.app;

import com.pluralsight.repository.CSVTransactionRepository;
import com.pluralsight.repository.TransactionRepository;
import com.pluralsight.service.LedgerService;
import com.pluralsight.service.LedgerServiceImpl;
import com.pluralsight.service.ScreenManager;
import com.pluralsight.view.HomeView;

public class LedgerApplication {
    public static void main(String[] args) {
        ScreenManager screen = new ScreenManager();

        // Start menu loop, handle user navigation
        screen.handleHomeMenu();



    }
}
