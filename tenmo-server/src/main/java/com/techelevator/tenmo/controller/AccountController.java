package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class AccountController {

    private AccountDao accountDao;
    private TransferDao transferDao;

    public AccountController(AccountDao accountDao, TransferDao transferDao) {
        this.accountDao = accountDao;
        this.transferDao = transferDao;
    }

    @RequestMapping(path = "balance/{id}", method = RequestMethod.GET)
    public BigDecimal getBalance(@PathVariable int id) {
        return accountDao.getBalanceById(id);
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
}
