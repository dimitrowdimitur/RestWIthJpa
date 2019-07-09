package com.example.restwithjpa.RestWithJpaProject.client;

import com.example.restwithjpa.RestWithJpaProject.transaction.MoneyTransaction;

import java.util.List;

public class ClientDTO {

    private long id;
    private String firstName;
    private String lastName;
    private String egn;
    private int amountOfAllTransactions;
    private List<MoneyTransaction> moneyTransaction;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEgn() {
        return egn;
    }

    public void setEgn(String egn) {
        this.egn = egn;
    }

    public int getAmountOfAllTransactions() {
        return amountOfAllTransactions;
    }

    public void setAmountOfAllTransactions(int amountOfAllTransactions) {
        this.amountOfAllTransactions = amountOfAllTransactions;
    }

    public List<MoneyTransaction> getMoneyTransaction() {
        return moneyTransaction;
    }

    public void setMoneyTransaction(List<MoneyTransaction> moneyTransaction) {
        this.moneyTransaction = moneyTransaction;
    }
}
