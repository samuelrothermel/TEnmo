package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;


public interface AccountDao {
    void subtractFromSendingAccount(double amount, int id);

    void addToReceivingAccount(double amount, int id);

    Double getBalanceFromAccountId(int accountId);
}
