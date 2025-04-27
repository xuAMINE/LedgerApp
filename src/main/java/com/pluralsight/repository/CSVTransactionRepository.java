package com.pluralsight.repository;

import com.pluralsight.model.Transaction;
import com.pluralsight.util.DateUtils;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CSVTransactionRepository implements TransactionRepository {
    private static final String FILE_PATH = "src/main/resources/transactions.csv";


    @Override
    public List<Transaction> findAll() {
        List<Transaction> transactions = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Transaction transaction = parseTransaction(line);
                if (transaction != null)
                    transactions.add(transaction);

            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load transactions: " + e.getMessage(), e);
        }

        transactions.sort((a, b) -> b.getDateTime().compareTo(a.getDateTime()));

        return transactions;
    }

    @Override
    public void save(Transaction transaction) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true));
            String formatted = formatTransaction(transaction);
            writer.write(formatted + System.lineSeparator());
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException("Failed to save transaction: " + e.getMessage(), e);
        }
    }

    private String formatTransaction(Transaction transaction) {
        return String.format("%s|%s|%s|%s",
                DateUtils.format(transaction.getDateTime()),
                transaction.getDescription().replace("|", ""),
                transaction.getVendor().replace("|", ""),
                transaction.getAmount().setScale(2, RoundingMode.HALF_UP).toPlainString()
        );
    }

    private Transaction parseTransaction(String line) {
        try {
            String[] parts = line.split("\\|");
            if (parts.length != 5)
                return null;

            LocalDateTime dateTime = DateUtils.parse(parts[0] + "|" + parts[1]); // date|time together
            String description = parts[2];
            String vendor = parts[3];
            BigDecimal amount = new BigDecimal(parts[4]);

            Transaction transaction = new Transaction();
            transaction.setDateTime(dateTime);
            transaction.setDescription(description);
            transaction.setVendor(vendor);
            transaction.setAmount(amount);

            return transaction;
        } catch (Exception e) {
            System.err.println("Failed to parse transaction line: " + line);
            return null;
        }
    }

}
