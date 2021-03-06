package com.techelevator.tenmo.services;

import com.techelevator.tenmo.App;
import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

// The service layer handles business requirements.
// Thus, it adheres to separation of concerns, isolating
// business logic from rest of application.
//
// Utilizes restTemplate to perform CRUD operations.

public class TransferService {

    private final String BASE_URL;
    private RestTemplate restTemplate = new RestTemplate();
    public AuthenticatedUser currentUser;

    public TransferService(String url, AuthenticatedUser currentUser) {
        this.currentUser = currentUser;
        BASE_URL = url;
    }

    public void sendBucks(Transfer newTransfer) {
        restTemplate.postForObject(BASE_URL + "/transfers", makeTransferEntity(newTransfer), Transfer.class);

    }

    public Transfer[] getAllTransfers(int id){
        Transfer[] transferArray;

        ResponseEntity<Transfer[]> results = restTemplate.exchange(BASE_URL + "/transfers/" + id,  HttpMethod.GET,
                makeAuthEntity(), Transfer[].class);

        transferArray = results.getBody();

        return transferArray;
    }

    public Transfer getTransferDetails(int id) {
        Transfer transfer;

        ResponseEntity<Transfer> results = restTemplate.exchange(BASE_URL + "/transfers/" + id + "/details", HttpMethod.GET, makeAuthEntity(), Transfer.class);
        transfer = results.getBody();

        return transfer;
    }

    private HttpEntity<Void> makeAuthEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(App.AUTH_TOKEN);
        HttpEntity entity = new HttpEntity<>(headers);
        return entity;
    }

    private HttpEntity<Void> makeTransferEntity(Transfer newTransfer) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(App.AUTH_TOKEN);
        HttpEntity entity = new HttpEntity<>(newTransfer, headers);
        return entity;
    }
}
