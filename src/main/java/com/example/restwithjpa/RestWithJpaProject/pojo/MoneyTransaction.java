package com.example.restwithjpa.RestWithJpaProject.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class MoneyTransaction {
    @Id
    @GeneratedValue
    private long id;

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
    public long getId() {
        return id;
    }

    /**
     * @return
     */
    public int getAmount() {
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
