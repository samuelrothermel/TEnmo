package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.User;

import java.util.List;

// The DAO class is responsible for encapsulating the details of the persistence layer
// and provides a CRUD interface for a single entity.
//
//To implement the DAO pattern, we'll first define a generic interface for the User table:

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
