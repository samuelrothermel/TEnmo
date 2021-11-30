package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.util.List;

// The DAO class is responsible for encapsulating the details of the persistence layer
// and provides a CRUD interface for a single entity.
//
//To implement the DAO pattern, we'll first define a generic interface for the Transfer table:

public interface TransferDao {

    void sendBucks(Transfer newTransfer);

    List<Transfer> getAllTransfers(int userId);

    Transfer getTransferDetails(int id);

}
