package com.techelevator.tenmo.model;

// The DAO class is responsible for encapsulating the details of the persistence layer
// and provides a CRUD interface for a single entity.
//
//To implement the DAO pattern, we create a domain class for the Account object:

public class Account {

    private Integer account_id;
    private Integer user_id;
    private Double balance;

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Integer getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Integer account_id) {
        this.account_id = account_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
}
