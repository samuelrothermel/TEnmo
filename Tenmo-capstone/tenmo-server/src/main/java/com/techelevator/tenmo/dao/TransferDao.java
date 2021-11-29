package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.util.List;


public interface TransferDao {

    void sendBucks(Transfer newTransfer);

    List<Transfer> getAllTransfers(int userId);

    Transfer getTransferDetails(int id);

}
