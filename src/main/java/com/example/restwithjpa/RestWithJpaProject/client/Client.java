package com.example.restwithjpa.RestWithJpaProject.client;

import com.example.restwithjpa.RestWithJpaProject.transaction.MoneyTransaction;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Client {

    @Id
    @GeneratedValue
    private Long id;

    private String firstName;
    private String lastName;
    private @Size(min = 10, max = 10, message = "EGN should be 10 characters") String egn;
    @Transient
    private Integer amountOfAllTransactions;

    @OneToMany(mappedBy = "client")
    private List<MoneyTransaction> moneyTransaction;

    public Client() {
    }

    /**
     * @param firstName
     * @param lastName
     * @param egn
     */
    public Client(String firstName, String lastName, @Size(min = 10, max = 10, message = "EGN should be 10 characters") String egn) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.egn = egn;
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
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return
     */
    public @Size(min = 10, max = 10, message = "EGN should be 10 characters") String getEgn() {
        return egn;
    }

    /**
     * @param egn
     */
    public void setEgn(@Size(min = 10, max = 10, message = "EGN should be 10 characters") String egn) {
        this.egn = egn;
    }

    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return
     */
    public List<MoneyTransaction> getMoneyTransaction() {
        return moneyTransaction;
    }

    /**
     * @param moneyTransaction
     */
    public void setMoneyTransaction(List<MoneyTransaction> moneyTransaction) {
        this.moneyTransaction = moneyTransaction;
    }

    /**
     * @return
     */
    public Integer getAmountOfAllTransactions() {
        return amountOfAllTransactions;
    }

    /**
     * @param amountOfAllTransactions
     */
    public void setAmountOfAllTransactions(int amountOfAllTransactions) {
        this.amountOfAllTransactions = amountOfAllTransactions;
    }
}
