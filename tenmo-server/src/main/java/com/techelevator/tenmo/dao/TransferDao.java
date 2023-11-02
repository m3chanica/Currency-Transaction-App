package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public interface TransferDao {

    List<Transfer> getTransfers();

    List<Transfer> getPendingTransfers();

    List<Transfer> getCompletedTransfers();

    Transfer sendTransfer(int fromUserId, int toUserId, BigDecimal amount);

    Transfer requestTransfer(int fromUserId, int toUserId, BigDecimal amount);

    Transfer getTransferById(int transferId);

    Transfer approveTransferRequest(int transferRequestId);
}
