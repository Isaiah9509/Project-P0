package com.revature.services;

import com.revature.daos.AccountDao;
import com.revature.daos.AccountDaoImp;
import com.revature.models.Account;
import com.revature.models.Person;

import java.util.List;
import java.util.Scanner;

public class AccountService {
    AccountDao accountDao = new AccountDaoImp();

    public boolean transferBalance(Account account1, Account account2){
        return false;
    }

    //Get all accounts and return them in a List.
    public List<Account> getAllAccounts(){
        return accountDao.getAllAccounts();
    }

    //Get an account by it's ID.
    public Account getAccountById(int id){
        return accountDao.getAccountById(id);
    }

//This method will be used to add to the current balance, and will return the new balance.
    public boolean addBalance(Account account){
        return accountDao.addBalance(account);
    }

    public boolean subtractBalance(Account account){
        return accountDao.subtractBalance(account);
    }

}
