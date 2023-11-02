package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.RegisterUserDto;
import com.techelevator.tenmo.model.User;

import java.math.BigDecimal;
import java.util.List;

public interface AccountDao {

    List<Account> getAccounts();

    Account getAccountByAccountId(int accountId);

    Account getAccountByUsername(String username);

    BigDecimal getBalanceById(int id);

}
