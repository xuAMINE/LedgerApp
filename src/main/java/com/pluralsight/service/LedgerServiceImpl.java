package com.pluralsight.service;

import com.pluralsight.model.SearchFilter;
import com.pluralsight.model.Transaction;
import com.pluralsight.repository.TransactionRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;

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
        System.out.println("Deposited " + transaction.getAmount() + " to your account.");
    }

    @Override
    public void makePayment(Transaction transaction) {
        if (transaction.getAmount().compareTo(BigDecimal.ZERO) > 0)
            throw new IllegalArgumentException("Payment amount must be negative.");


        if (transaction.getDateTime() == null)
            transaction.setDateTime(LocalDateTime.now());


        transactionRepository.save(transaction);
        System.out.println("Payment of " + transaction.getAmount() + " was successful.");
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
        return transactionRepository.findAll();
    }

    @Override
    public List<Transaction> getDeposits() {
        return transactionRepository.findAll()
                .stream()
                .filter(t -> t.getAmount().compareTo(BigDecimal.ZERO) > 0)
                .collect(Collectors.toList());
    }

    @Override
    public List<Transaction> getPayments() {
        return transactionRepository.findAll()
                .stream()
                .filter(t -> t.getAmount().compareTo(BigDecimal.ZERO) < 0)
                .collect(Collectors.toList());
    }

    @Override
    public List<Transaction> getMonthToDateTransactions() {
        LocalDate today = LocalDate.now();
        LocalDate firstDayOfMonth = today.withDayOfMonth(1);

        return transactionRepository.findAll()
                .stream()
                .filter(t -> t.getDateTime().toLocalDate().isAfter(firstDayOfMonth))
                .collect(Collectors.toList());
    }

    @Override
    public List<Transaction> getPreviousMonthTransactions() {
        LocalDate today = LocalDate.now();
        YearMonth previousMonth = YearMonth.from(today.minusMonths(1));
        LocalDate start = previousMonth.atDay(1);
        LocalDate end = previousMonth.atEndOfMonth();

        return transactionRepository.findAll()
                .stream()
                .filter(t -> t.getDateTime().toLocalDate().isAfter(start)
                          && t.getDateTime().toLocalDate().isBefore(end))
                .collect(Collectors.toList());
    }

    @Override
    public List<Transaction> getYearToDateTransactions() {
        LocalDate today = LocalDate.now();
        LocalDate startOfYear = today.withDayOfYear(1);

        return transactionRepository.findAll()
                .stream()
                .filter(t -> t.getDateTime().toLocalDate().isAfter(startOfYear))
                .collect(Collectors.toList());
    }

    @Override
    public List<Transaction> getPreviousYearTransactions() {
        LocalDate today = LocalDate.now();
        LocalDate startOfYear = today.minusYears(1).withDayOfYear(1);
        LocalDate endOfYear = today.minusYears(1).withMonth(12).withDayOfMonth(31);

        return transactionRepository.findAll()
                .stream()
                .filter(t -> {
                    LocalDate date = t.getDateTime().toLocalDate();
                    return date.isAfter(startOfYear) && date.isBefore(endOfYear);
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<Transaction> getTransactionsByVendor(String vendor) {
        return transactionRepository.findAll()
                .stream()
                .filter(t -> t.getVendor().equalsIgnoreCase(vendor))
                .collect(Collectors.toList());
    }

    @Override
    public List<Transaction> search(SearchFilter filter) {
        return transactionRepository.findAll()
                .stream()
                .filter(t -> {
                    if (filter.getStartDate() != null && t.getDateTime().toLocalDate().isBefore(filter.getStartDate()))
                        return false;

                    if (filter.getEndDate() != null && t.getDateTime().toLocalDate().isAfter(filter.getEndDate()))
                        return false;

                    if (filter.getDescription() != null && !t.getDescription().isBlank() && !t.getDescription().toLowerCase().contains(filter.getDescription().toLowerCase()))
                        return false;

                    if (filter.getVendor() != null && !t.getVendor().isBlank() && !t.getVendor().toLowerCase().contains(filter.getVendor().toLowerCase()))
                        return false;

                    return filter.getAmount() == null || t.getAmount().compareTo(filter.getAmount()) == 0;
                })
                .collect(Collectors.toList());
    }
}
