package com.revature.daos;

import com.revature.models.Account;
import com.revature.models.Person;

import java.util.List;

public interface AccountDao {

    public boolean deleteAccount(int id);

    public boolean closeAccount(int id);

    public boolean checkIfOpen(int id);

    public boolean openAccount(int id);

    public List<Account> getAllAccounts();

    public boolean addBalance(Account account);

    public boolean subtractBalance(Account account);

    public Account getAccountById(int id);

    public boolean applyForAccount(Person person);

    }
