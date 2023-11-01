package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Account;
import com.techelevator.util.BasicLogger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

public class AccountService {

    private final String baseUrl;
    private final RestTemplate restTemplate = new RestTemplate();

    public AccountService(String url) {
        this.baseUrl = url;
    }

    public BigDecimal getBalance(String username) {
        BigDecimal accountBalance = BigDecimal.valueOf(0.01);
        Account account = null;

        try {
            ResponseEntity<Account> response = restTemplate.getForEntity(baseUrl + "balance/" + username, Account.class, HttpMethod.GET);
            account = response.getBody();
            accountBalance = account.getBalance();

        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }

        return accountBalance;
    }


}
