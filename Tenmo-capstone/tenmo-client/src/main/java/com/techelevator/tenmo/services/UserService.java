package com.techelevator.tenmo.services;


import com.techelevator.tenmo.App;
import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class UserService {

    private final String BASE_URL;
    private RestTemplate restTemplate = new RestTemplate();
    public AuthenticatedUser currentUser;

    public UserService(String url, AuthenticatedUser currentUser) {
        this.currentUser = currentUser;
        BASE_URL = url;
    }

    public double getBalance(int id){

        ResponseEntity<Double> results =
                restTemplate.exchange(BASE_URL + "accounts/" + id + "/balance",
                        HttpMethod.GET, makeAuthEntity(), Double.class);

        double balance = results.getBody();

        return balance;
    }

//    public double getReceiverBalance(int id){
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<Integer> entity = new HttpEntity<>(id, headers);
//
//        ResponseEntity<Double> results =
//                restTemplate.exchange(BASE_URL + "accounts/balance/" + id,
//                        HttpMethod.GET, entity, Double.class);
//
//        double balance = results.getBody();
//        return balance;
//    }

    public User[] getUsers() {

        ResponseEntity<User[]> results = restTemplate.exchange(BASE_URL + "user",
                HttpMethod.GET, makeAuthEntity(), User[].class);

        User[] usersArray = results.getBody();
        return usersArray;
    }

    public Integer getAccountIdByUserID(int id) {

        ResponseEntity<Integer> results = restTemplate.exchange(BASE_URL + "accounts/" + id, HttpMethod.GET, makeAuthEntity(), Integer.class);

        int accountId = results.getBody();

        return accountId;
    }

//    public Integer getUserIdByAccountId(int id) {
//
//        ResponseEntity<Integer> results = restTemplate.exchange(BASE_URL + "users/" + id, HttpMethod.GET, makeAuthEntity(), Integer.class);
//
//        int userId = results.getBody();
//
//        return userId;
//    }

    public String getUsernameFromAccountId(int id) {
        ResponseEntity<String> result = restTemplate.exchange(BASE_URL + "users/accounts/" + id, HttpMethod.GET, makeAuthEntity(), String.class);
        String username = result.getBody();
        return username;
    }

    private HttpEntity makeAuthEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(App.AUTH_TOKEN);
        HttpEntity entity = new HttpEntity<>(headers);
        return entity;
    }
}
