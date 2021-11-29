package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.services.TransferService;
import com.techelevator.tenmo.services.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
public class TransferController {

    private TransferService transferService;

    public TransferController(TransferService transferService){
        this.transferService = transferService;
    }

    @RequestMapping(path = "/transfers", method = RequestMethod.POST)
    public void sendBucks(@RequestBody Transfer newTransfer){
        transferService.sendBucks(newTransfer);
    }

    @RequestMapping(path = "/transfers/{id}", method = RequestMethod.GET)
    public List<Transfer> getAllTransfers(@PathVariable int id){ return transferService.getAllTransfers(id); }

    @RequestMapping(path = "/transfers/{id}/details", method = RequestMethod.GET)
    public Transfer getTransferDetails(@PathVariable int id){ return transferService.getTransferDetails(id);}



}
