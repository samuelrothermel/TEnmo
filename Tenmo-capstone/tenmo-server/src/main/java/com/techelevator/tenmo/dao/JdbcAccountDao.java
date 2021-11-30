package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.services.UserService;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

// This class is the implementation of AccountDao.
// Utilizes the JdbcTemplate, allowing us to work with our SQL relational database and JDBC

@Component
public class JdbcAccountDao implements AccountDao{

    private JdbcTemplate jdbcTemplate;
    private UserDao userDao;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate, UserDao userDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.userDao = userDao;
    }

    @Override
    public void subtractFromSendingAccount(double amount, int id){
        Double existingBalance = getBalanceFromAccountId(id);
        Double newBalance = existingBalance - amount;
        String sql = "UPDATE accounts SET balance = ? WHERE account_id = ?";
        jdbcTemplate.update(sql, newBalance, id);
    }

    @Override
    public void addToReceivingAccount(double amount, int id) {
        Double existingBalance = getBalanceFromAccountId(id);
        Double newBalance = existingBalance + amount;
        String sql = "UPDATE accounts SET balance = ? WHERE account_id = ?";
        jdbcTemplate.update(sql, newBalance, id);
    }

    @Override
    public Double getBalanceFromAccountId(int accountId) {
        String sql = "SELECT balance FROM accounts WHERE account_id = ?";
        Double balance = jdbcTemplate.queryForObject(sql, Double.class, accountId);
        return balance;
    }

//    private Account mapRowToAccount(SqlRowSet result) {
//        Account account = new Account();
//        account.setBalance(result.getDouble("balance"));
//        account.setAccount_id(result.getInt("account_id"));
//        account.setUser_id(result.getInt("user_id"));
//        return account;
//    }


}
