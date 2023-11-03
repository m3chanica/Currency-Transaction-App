package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.util.BasicLogger;
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


    public BigDecimal getBalance(AuthenticatedUser user) {
        BigDecimal accountBalance = null;
        Account account = null;

        try {
            ResponseEntity<BigDecimal> response = restTemplate.getForEntity(baseUrl + "balance/" + user.getUser().getUserId(), BigDecimal.class, HttpMethod.GET);
            accountBalance = response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }

        return accountBalance;
    }
    public String getUsernameByAccountId(int accountId) {
        String username = "";
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(baseUrl + "username/" + accountId,  String.class, HttpMethod.GET);
            username = response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return username;
    }
}