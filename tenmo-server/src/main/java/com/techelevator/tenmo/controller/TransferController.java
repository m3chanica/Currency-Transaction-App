package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransferController {

    private TransferDao transferDao;

    public TransferController(TransferDao transferDao) {
        this.transferDao = transferDao;
    }

    @RequestMapping(path = "transfers", method = RequestMethod.GET)
    public List<Transfer> getAllTransfers() {
        return transferDao.getTransfers();
    }

    @RequestMapping(path = "transfers/{id}", method = RequestMethod.GET)
    public Transfer getTransferById(@PathVariable int transferId) {
        return transferDao.getTransferById(transferId);
    }

    @RequestMapping(path = "transfers/pending", method = RequestMethod.GET)
    public List<Transfer> getPendingTransfers() {
        return transferDao.getPendingTransfers();
    }

    @RequestMapping(path = "transfers/completed", method = RequestMethod.GET)
    public List<Transfer> getCompletedTransfers() {
        return transferDao.getCompletedTransfers();
    }

//    @RequestMapping(path = "transfers", method = );


}
