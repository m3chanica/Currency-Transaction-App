package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.model.Account;
import org.springframework.web.bind.annotation.*;
import com.techelevator.tenmo.dao.AccountDao;
import java.math.BigDecimal;
import java.util.List;

@RestController
public class AccountController {
    private AccountDao accountDao;

    public AccountController(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @RequestMapping(path = "/accounts", method = RequestMethod.GET)
    public List<Account> getAccounts() {
        return accountDao.getAccounts();
    }

    @RequestMapping(path = "/accounts/{accountId}", method = RequestMethod.GET)
    public Account getAccountByAccountId(@PathVariable int accountId) {
        return accountDao.getAccountByAccountId(accountId);
    }

    @RequestMapping(path = "/accounts/user/{userId}", method = RequestMethod.GET)
    public Account getAccountByUserId(@PathVariable int userId) {
        return accountDao.getAccountByUserId(userId);
    }

    @RequestMapping(path = "/balance/{userId}", method = RequestMethod.GET)
    public BigDecimal getBalanceByUserId(@PathVariable int userId) {
        return accountDao.getBalanceByUserId(userId);
    }
}