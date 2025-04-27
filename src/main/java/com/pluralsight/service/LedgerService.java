package com.pluralsight.service;

import com.pluralsight.model.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface LedgerService {

    void addDeposit(Transaction transaction);

    void makePayment(Transaction transaction);

    BigDecimal getBalance();

    List<Transaction> getAllTransactions();

    List<Transaction> getDeposits();

    List<Transaction> getPayments();

    List<Transaction> getTransactionsByVendor(String vendor);

    List<Transaction> getMonthToDateTransactions();

    List<Transaction> getPreviousMonthTransactions();

    List<Transaction> getYearToDateTransactions();

    List<Transaction> getPreviousYearTransactions();

}
