package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.TransferRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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

    @RequestMapping(path = "transfers/{transferId}", method = RequestMethod.GET)
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

    @RequestMapping(path = "transfers/newTransfer", method = RequestMethod.POST)
    public ResponseEntity<Transfer> createTransfer(@RequestBody TransferRequestDTO transferRequest) {
        Transfer transfer = transferDao.createTransfer(transferRequest);

        if (transfer != null) {
            return new ResponseEntity<>(transfer, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}