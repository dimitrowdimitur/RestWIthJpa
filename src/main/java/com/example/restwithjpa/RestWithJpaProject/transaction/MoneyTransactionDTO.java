package com.example.restwithjpa.RestWithJpaProject.transaction;

import com.example.restwithjpa.RestWithJpaProject.client.Client;

public class MoneyTransactionDTO {

    private long id;
    private int amount;
    private Client client;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
