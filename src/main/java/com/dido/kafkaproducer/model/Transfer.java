package com.dido.kafkaproducer.model;

public class Transfer {
    private final int fromAccount;
    private final int toAccount;
    private final Long amount;

    public Transfer(int fromAccount, int toAccount, Long amount) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
    }

    public int getFromAccount() {
        return fromAccount;
    }

    public int getToAccount() {
        return toAccount;
    }

    public Long getAmount() {
        return amount;
    }
}
