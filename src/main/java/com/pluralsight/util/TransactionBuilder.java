package com.pluralsight.util;

import com.pluralsight.model.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionBuilder {

    public Transaction buildTransaction(String description, String vendor, BigDecimal amount) {
        Transaction transaction = new Transaction();
        transaction.setDateTime(LocalDateTime.now());
        transaction.setDescription(description);
        transaction.setVendor(vendor);
        transaction.setAmount(amount);

        return transaction;
    }
}
