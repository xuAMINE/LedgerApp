package com.pluralsight.repository;

import com.pluralsight.model.Transaction;

import java.util.List;

public interface TransactionRepository {

    List<Transaction> findAll();

    void save(Transaction transaction);
}
