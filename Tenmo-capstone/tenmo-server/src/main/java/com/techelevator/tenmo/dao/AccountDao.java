package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

// The DAO class is responsible for encapsulating the details of the persistence layer
// and provides a CRUD interface for a single entity.
//
//To implement the DAO pattern, we'll first define a generic interface for the Account table:

public interface AccountDao {
    void subtractFromSendingAccount(double amount, int id);

    void addToReceivingAccount(double amount, int id);

    Double getBalanceFromAccountId(int accountId);
}
