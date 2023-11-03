package com.techelevator.tenmo.dao;

import java.math.BigDecimal;
import java.util.List;

import com.techelevator.tenmo.model.Account;

public interface AccountDao {
    List<Account> getAccounts();
    Account getAccountByAccountId(int accountId);
    Account getAccountByUserId(int userId);
    BigDecimal getBalanceByUserId(int userId);
    String getUsernameByAccountId(int accountId);
}
