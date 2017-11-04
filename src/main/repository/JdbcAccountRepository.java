package main.repository;

import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by GHAITH on 30/10/2017.
 */
public class JdbcAccountRepository implements AccountRepository {

    @Autowired
    private DataSource dataSource;

    public JdbcAccountRepository(DataSource ds) {
        this.dataSource = ds;
    }

    public JdbcAccountRepository() {
    }

    private Connection getConnection() {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public ResultSet loadAccount(long id) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = getConnection().prepareStatement("SELECT * FROM account WHERE id = ?");
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String testLoadAccount(long id) {
        String balance = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = getConnection().prepareStatement("SELECT * FROM accountTest WHERE id = ?");
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                balance = rs.getString("balance");
                System.out.println("Balance :" + rs.getString("balance"));
            }
            return balance;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return balance;
    }

    @Override
    public Double debit(Double amount, long accountId) {
        ResultSet rs = loadAccount(accountId);
        Double newBalance = null;
        try {
            while (rs.next()) {
                String balance = rs.getString("balance");
                newBalance = Double.parseDouble(balance);
                newBalance = newBalance - amount;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return newBalance;
    }

    @Override
    public Double credit(Double amount, long accountId) {
        ResultSet rs = loadAccount(accountId);
        Double newBalance = null;
        try {
            while (rs.next()) {
                String balance = rs.getString("balance");
                newBalance = Double.parseDouble(balance);
                newBalance = newBalance + amount;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return newBalance;
    }

    @Override
    public void updateAccount(Double amount, long accountId) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = getConnection().prepareStatement("UPDATE account\n" +
                    "SET  balance = ? WHERE id = ?");
            preparedStatement.setDouble(1, amount);
            preparedStatement.setLong(2, accountId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Double monetaryAmount(Double amount, long creditAccountId, long debitAccountId) {
        Double newBalance1 = credit(amount, creditAccountId);
        Double newBalance2 = debit(amount, debitAccountId);
        updateAccount(newBalance1, creditAccountId);
        updateAccount(newBalance2, debitAccountId);
        return newBalance1;
    }
}
