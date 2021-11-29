package com.techelevator.tenmo.services;


import com.techelevator.tenmo.dao.JdbcUserDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Service
public class UserService {

    private UserDao userDao;

    public UserService(UserDao userDao){
        this.userDao = userDao;
    }

    //Method to get Account Id
    public int getAccountId(int id){
        return userDao.getAccountId(id);
    }

    //Method to get the balance of an account base on account id
    public double getBalance(int id){
        return userDao.getBalance(id);
    }

    public double getReceiverBalance(int id){
        return userDao.getReceiverBalance(id);
    }

    //Method to get an array of users
    public User[] findAll() {
        List<User> userList = userDao.findAll();
        User[] users = new User[userList.size()];
        users = userList.toArray(users);
        return users;
    }

    public String getUsernameFromAccountId(int id) {return userDao.getUsernameFromAccountId(id);}
}
