package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.exception.DaoException;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTransferDao implements TransferDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcTransferDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Transfer> getTransfers() {
        List<Transfer> listOfTransfers = new ArrayList<>();
        String sql = "SELECT transfer_id, transfer_type_id, transfer_status_id, account_from, " +
                "account_to, amount FROM transfer;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while(results.next()) {
                Transfer newTransfer = mapRowToTransfer(results);

                listOfTransfers.add(newTransfer);

            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return listOfTransfers;
    }

    public List<Transfer> getPendingTransfers() {
        List<Transfer> listOfPendingTransfers = new ArrayList<>();
        String sql = "SELECT transfer_id, transfer_type_id, transfer_status_id, account_from, " +
                "account_to, amount FROM transfer WHERE transfer_status_id = 1;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while(results.next()) {
                Transfer newTransfer = mapRowToTransfer(results);

                listOfPendingTransfers.add(newTransfer);

            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return listOfPendingTransfers;
    }

    public List<Transfer> getCompletedTransfers() {
        List<Transfer> listOfCompletedTransfers = new ArrayList<>();
        String sql = "SELECT transfer_id, transfer_type_id, transfer_status_id, account_from, " +
                "account_to, amount FROM transfer WHERE transfer_status_id = 2;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while(results.next()) {
                Transfer newTransfer = mapRowToTransfer(results);

                listOfCompletedTransfers.add(newTransfer);

            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return listOfCompletedTransfers;
    }

    public Transfer sendTransfer(int fromUserId, int toUserId, BigDecimal amount) {
        Transfer sendingTransfer = new Transfer();
        String sql = "INSERT INTO transfer (transfer_type_id, transfer_status_id, account_from, account_to, amount) " +
                "VALUES (2, 2, (SELECT account_id FROM account WHERE user_id = ?), (SELECT account_id FROM account WHERE user_id = ?), ?) " +
                "RETURNING transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, fromUserId, toUserId, amount);
            if(results.next()) {
                sendingTransfer = mapRowToTransfer(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return sendingTransfer;
    }

    public Transfer requestTransfer(int fromUserId, int toUserId, BigDecimal amount) {
        Transfer transferRequest = new Transfer();
        String sql = "INSERT INTO transfer (transfer_type_id, transfer_status_id, account_from, account_to, amount) " +
                "VALUES (1, 1, (SELECT account_id FROM account WHERE user_id = ?), (SELECT account_id FROM account WHERE user_id = ?), ?) " +
                "RETURNING transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, fromUserId, toUserId, amount);
            if(results.next()) {
                transferRequest = mapRowToTransfer(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return transferRequest;
    }

    public Transfer getTransferById(int transferId) {
        Transfer transfer = new Transfer();
        String sql = "SELECT transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount " +
                "FROM transfer WHERE transfer_id = ?;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, transferId);
            if(results.next()) {
                transfer = mapRowToTransfer(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return transfer;
    }

    public Transfer approveTransferRequest(int transferRequestId) {
        Transfer approvedTransfer = null;
        String sql = "UPDATE transfer SET transfer_status_id = 2 WHERE transfer_id = ? " +
                "RETURNING RETURNING transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, transferRequestId);
            if(results.next()) {
                approvedTransfer = mapRowToTransfer(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return approvedTransfer;
    }

    private Transfer mapRowToTransfer(SqlRowSet rs) {
        Transfer transfer = new Transfer();
        transfer.setTransferId(rs.getInt("transfer_id"));
        transfer.setTransferTypeId(rs.getInt("transfer_type_id"));
        transfer.setTransferStatusId(rs.getInt("transfer_status_id"));
        transfer.setAccountFrom(rs.getInt("account_from"));
        transfer.setAccountTo(rs.getInt("account_to"));
        transfer.setAmount(rs.getBigDecimal("amount"));
        return transfer;
    }
}
