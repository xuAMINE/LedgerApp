package com.pluralsight.service;

import com.pluralsight.model.Transaction;
import com.pluralsight.repository.TransactionRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class LedgerServiceImpl implements LedgerService {
    private final TransactionRepository transactionRepository;

    public LedgerServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void addDeposit(Transaction transaction) {
        if (transaction.getAmount().compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("Deposit amount must be positive.");


        if (transaction.getDateTime() == null)
            transaction.setDateTime(LocalDateTime.now());

        transactionRepository.save(transaction);
    }

    @Override
    public void makePayment(Transaction transaction) {
        if (transaction.getAmount().compareTo(BigDecimal.ZERO) > 0)
            throw new IllegalArgumentException("Payment amount must be negative.");


        if (transaction.getDateTime() == null)
            transaction.setDateTime(LocalDateTime.now());


        transactionRepository.save(transaction);
    }

    @Override
    public BigDecimal getBalance() {
        List<Transaction> transactions = transactionRepository.findAll();
        return transactions.stream()
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return List.of();
    }

    @Override
    public List<Transaction> getDeposits() {
        return List.of();
    }

    @Override
    public List<Transaction> getPayments() {
        return List.of();
    }

    @Override
    public List<Transaction> getTransactionsByVendor(String vendor) {
        return List.of();
    }

    @Override
    public List<Transaction> getMonthToDateTransactions() {
        return List.of();
    }

    @Override
    public List<Transaction> getPreviousMonthTransactions() {
        return List.of();
    }

    @Override
    public List<Transaction> getYearToDateTransactions() {
        return List.of();
    }

    @Override
    public List<Transaction> getPreviousYearTransactions() {
        return List.of();
    }
}
