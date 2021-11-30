package com.techelevator.tenmo.services;

import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

// The DAO layer's main goal is to handle the details of the persistence mechanism.
// Meanwhile, the Service layer stands on top of it to handle business requirements.
// This Transfer Service layer references the Transfer DAO interface.

@Service
public class TransferService {

    private TransferDao transferDao;


    public TransferService(TransferDao transferDao){
        this.transferDao = transferDao;
    }

    public void sendBucks(Transfer newTransfer){
        transferDao.sendBucks(newTransfer);
    }

    public List<Transfer> getAllTransfers(int id){ return transferDao.getAllTransfers(id); }

    public Transfer getTransferDetails(int id){ return transferDao.getTransferDetails(id);}




}
