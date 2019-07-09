package com.example.restwithjpa.RestWithJpaProject.transaction;

import com.example.restwithjpa.RestWithJpaProject.client.Client;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
public class MoneyTransaction {
    @Id
    @GeneratedValue
    private Long id;

    @Min(1)
    private int amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Client client;

    public MoneyTransaction() {
    }

    /**
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     * @return
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * @param amount
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * @return
     */
    public Client getClient() {
        return client;
    }

    /**
     * @param client
     */
    public void setClient(Client client) {
        this.client = client;
    }
}
