package main.repository;

import java.sql.ResultSet;

/**
 * Created by GHAITH on 30/10/2017.
 */
public interface AccountRepository {

    String testLoadAccount(long id);

    ResultSet loadAccount(long id);

    Double debit(Double amount, long accountId);

    Double credit(Double amount, long accountId);

    void updateAccount(Double amount, long accountId);

    Double monetaryAmount(Double amount, long creditAccountId, long debitAccountId);
}
