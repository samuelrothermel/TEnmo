package com.techelevator.tenmo.model;

import java.math.BigDecimal;

// The DAO class is responsible for encapsulating the details of the persistence layer
// and provides a CRUD interface for a single entity.
//
//To implement the DAO pattern, we create a domain class for the Transfer object:

public class Transfer {

    private Integer transferId;
    private Integer transferTypeId;
    private Integer transferStatusId;
    private Integer accountFrom;
    private Integer accountTo;
    private Double amount;

    public Transfer() {}

    public Integer getTransferId() {
        return transferId;
    }

    public void setTransferId(Integer transferId) {
        this.transferId = transferId;
    }

    public Integer getTransferTypeId() {
        return transferTypeId;
    }

    public void setTransferTypeId(Integer transferTypeId) {
        this.transferTypeId = transferTypeId;
    }

    public Integer getTransferStatusId() {
        return transferStatusId;
    }

    public void setTransferStatusId(Integer transferStatusId) {
        this.transferStatusId = transferStatusId;
    }

    public Integer getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(Integer accountFrom) {
        this.accountFrom = accountFrom;
    }

    public Integer getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(Integer accountTo) {
        this.accountTo = accountTo;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
