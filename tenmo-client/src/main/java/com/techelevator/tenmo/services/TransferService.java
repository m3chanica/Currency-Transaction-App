package com.techelevator.tenmo.services;


import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.util.BasicLogger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

public class TransferService {
    private final String baseUrl;
    private final RestTemplate restTemplate = new RestTemplate();

    public TransferService(String url) {
        this.baseUrl = url;
    }


    //(readme 4)
    //sendTransfer method
    //specific amount
    //need userId
    //can't send to self
    //cant send more than currentBalance
    //transfer includes fromUserId and toUserId
    //sender balance reduces, receiver balance increases
    // can't have 0 amount or must be positive
    //initial status of "approved"

    //(readme 5)
    //listTransfer method
    public Transfer[] listTransfer(){
        Transfer[] transfers = null;
        try{
            transfers = restTemplate.getForObject(baseUrl + "transfers/", Transfer[].class);
        } catch (RestClientResponseException | ResourceAccessException e){
            BasicLogger.log(e.getMessage());
        }
        return transfers;
    }


    //(readme 6)
    //transfer details by Id
    public Transfer getTransferDetails(int transferId) {
        Transfer transfer = null;
        try {
            transfer = restTemplate.getForObject(baseUrl + "transfers/" + transferId, Transfer.class);
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return transfer;
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


    //(readme 7)
    //requestTransfer method
    //can not request from self
    //can't be 0 must be positive amount
    //includes fromUserId and toUserId
    //initial status of "pending"
    //nothing changes until request is "approved"
    //transfer request should appear in both users list of transfers

    //(readme 8)
    //see pendingTransfers method

    //(readme 9)
    //updateRequest "approve" or "reject" pending transfers
}
