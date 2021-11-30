package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

// This class is the implementation of TransferDao.
// Utilizes the JdbcTemplate, allowing us to work with our SQL relational database and JDBC

@Component
public class JdbcTransferDao implements TransferDao{

    private JdbcTemplate jdbcTemplate;
    private JdbcAccountDao jdbcAccountDao;
    private AccountDao accountDao;
    private UserDao userDao;


    public JdbcTransferDao(JdbcTemplate jdbcTemplate, JdbcAccountDao jdbcAccountDao, AccountDao accountDao, UserDao userDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcAccountDao = jdbcAccountDao;
        this.accountDao = accountDao;
        this.userDao = userDao;
    }

    @Override
    public void sendBucks(Transfer newTransfer) {

        String sql = "INSERT INTO transfers (transfer_id, transfer_type_id, transfer_status_id, " +
                "account_from, account_to, amount) VALUES  (DEFAULT, ?, ?, ?, ?, ?);";

           jdbcTemplate.update(sql, newTransfer.getTransferTypeId(), newTransfer.getTransferStatusId(),
                   newTransfer.getAccountFrom(), newTransfer.getAccountTo(), newTransfer.getAmount());

        accountDao.subtractFromSendingAccount(newTransfer.getAmount(), newTransfer.getAccountFrom());
        accountDao.addToReceivingAccount(newTransfer.getAmount(), newTransfer.getAccountTo());


    }

    //This method gets a list of transfers (receive or send) based on accountId
    @Override
    public List<Transfer> getAllTransfers(int id) {
        List<Transfer> transferList = new ArrayList<>();

        String sql = "SELECT * FROM transfers t " +
        "JOIN accounts a ON a.account_id = t.account_from OR a.account_id = t.account_to " +
        "JOIN users y ON y.user_id = a.user_id " +
        "WHERE y.user_id = ?";

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, id);

        while(rs.next()){
            Transfer transfer = mapRowToTransfer(rs);
            transferList.add(transfer);
        }

        return transferList;
    }

    @Override
    public Transfer getTransferDetails(int id){
        Transfer transferDetails = null;

        String sql = "SELECT * FROM transfers WHERE transfer_id = ?";

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, id);

        while(rs.next()) {
            transferDetails = mapRowToTransfer(rs);

        }

        return transferDetails;
    }

    private Transfer mapRowToTransfer(SqlRowSet rs) {
        Transfer transfer = new Transfer();
        transfer.setTransferId(rs.getInt("transfer_id"));
        transfer.setTransferTypeId(rs.getInt("transfer_type_id"));
        transfer.setTransferStatusId(rs.getInt("transfer_status_id"));
        transfer.setAccountFrom(rs.getInt("account_from"));
        transfer.setAccountTo(rs.getInt("account_to"));
        transfer.setAmount(rs.getDouble("amount"));
        return transfer;
    }


}

