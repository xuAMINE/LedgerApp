package com.pluralsight.model;

import com.pluralsight.app.LedgerApplication;
import com.pluralsight.util.DateUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {
    private LocalDateTime dateTime;
    private String description;
    private String vendor;
    private BigDecimal amount;

    public Transaction(String description, String vendor, BigDecimal amount) {
        this.dateTime = LocalDateTime.now();
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    public Transaction() {
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "dateTime=" + DateUtils.format(dateTime) +
                ", description='" + description + '\'' +
                ", vendor='" + vendor + '\'' +
                ", amount=" + amount +
                '}';
    }
}
