package com.revature.daos;

import com.revature.models.Account;

import java.util.List;

public interface AccountDao {

    public boolean transferBalance(Account account1, Account account2);

    public List<Account> getAllAccounts();

    public boolean addBalance(Account account);

    public boolean subtractBalance(Account account);

    public Account getAccountById(int id);

    }
