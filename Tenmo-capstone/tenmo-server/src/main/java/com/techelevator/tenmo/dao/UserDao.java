package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.User;

import java.util.List;

public interface UserDao {

    List<User> findAll();

    User findByUsername(String username);

    int findIdByUsername(String username);

    boolean create(String username, String password);

    int getAccountId(int id);

    double getBalance(int id);

    double getReceiverBalance(int id);

    String getUsernameFromAccountId(int id);
}
