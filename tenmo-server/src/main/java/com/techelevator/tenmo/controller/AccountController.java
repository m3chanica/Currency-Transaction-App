package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
public class AccountController {

    private AccountDao accountDao;

    public AccountController(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @RequestMapping(path = "balance/{username}", method = RequestMethod.GET)
    public BigDecimal getBalance(@PathVariable String username) {
        return accountDao.getBalanceByUsername(username);
    }
}
