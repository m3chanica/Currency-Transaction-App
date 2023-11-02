package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;

import java.util.ArrayList;
import java.util.List;

public interface TransferDao {

    List<Transfer> getTransfers();

    List<Transfer> getPendingTransfers();

    List<Transfer> getCompletedTransfers();

    Transfer sendTransfer();

    Transfer requestTransfer();

    Transfer getTransferById(int transferId);

    Transfer setStatus();
}
