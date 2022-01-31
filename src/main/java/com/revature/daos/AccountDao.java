package com.revature.daos;

import com.revature.models.Account;

import java.util.List;

public interface AccountDao {

    public List<Account> getAllAccounts();

    public boolean addBalance(Account account);

    public boolean subtractBalance(Account account);

    public Account getAccountById(int id);

    }
