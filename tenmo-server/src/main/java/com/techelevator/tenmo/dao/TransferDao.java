package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.TransferRequestDTO;
import com.techelevator.tenmo.model.User;

import java.util.List;

public interface TransferDao {

    List<Transfer> getTransfers();

    List<Transfer> getPendingTransfers();

    List<Transfer> getCompletedTransfers();

    Transfer createTransfer(TransferRequestDTO transferRequest);

    Transfer getTransferById(int transferId);

    Transfer approveTransferRequest(TransferRequestDTO transferRequest, int transferRequestId);
}