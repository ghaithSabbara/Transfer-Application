package main.services;

import main.repository.AccountRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by GHAITH on 30/10/2017.
 */
public class TransferServiceImpl implements TransferService {

    private AccountRepository accountRepository;

    public TransferServiceImpl(AccountRepository ar) {
        this.accountRepository = ar;
    }

    public AccountRepository getAccountRepository() {
        return accountRepository;
    }

    public void setAccountRepository(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public ResultSet loadAccount(long id) {
        return accountRepository.loadAccount(id);
    }

    @Override
    public Double debit(Double amount, long accountId) {
        return accountRepository.debit(amount, accountId);
    }

    @Override
    public Double credit(Double amount, long accountId) {
        return accountRepository.credit(amount, accountId);
    }

    @Override
    public void updateAccount(Double amount, long accountId) {
        accountRepository.updateAccount(amount, accountId);
    }

    @Override
    public Double monetaryAmount(Double amount, long creditAccountId, long debitAccountId) {
        return accountRepository.monetaryAmount(amount, creditAccountId, debitAccountId);
    }

    @Override
    public String testLoadAccount(long id) {
        return accountRepository.testLoadAccount(id);
    }
}
