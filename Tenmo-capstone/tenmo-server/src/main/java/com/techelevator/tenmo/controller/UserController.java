package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.services.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("isAuthenticated()")
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(path = "/accounts/{id}", method = RequestMethod.GET)
    public int getAccountId(@PathVariable int id){
        return userService.getAccountId(id);
    }

    @RequestMapping(path = "/accounts/{id}/balance", method = RequestMethod.GET)
    public double getBalance(@PathVariable int id){
        return userService.getBalance(id);
    }

    @RequestMapping(path = "/accounts/balance/{id}", method = RequestMethod.GET)
    public double getReceiverBalance(@PathVariable int id){
        return userService.getReceiverBalance(id);
    }

    @RequestMapping(path = "/user", method = RequestMethod.GET)
    public User[] getUsers() {
        return userService.findAll();
    }

    @RequestMapping(path = "/users/accounts/{id}", method = RequestMethod.GET)
    public String getUsernameFromAccountId(@PathVariable int id) {return userService.getUsernameFromAccountId(id);}
}
