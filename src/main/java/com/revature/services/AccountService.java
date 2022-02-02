package com.revature.services;

import com.revature.daos.AccountDao;
import com.revature.daos.AccountDaoImp;
import com.revature.models.Account;
import com.revature.models.Person;
import com.revature.utils.LoggingUtil;
import io.javalin.http.UnauthorizedResponse;

import java.util.List;
import java.util.Scanner;

public class AccountService {
    AccountDao accountDao = new AccountDaoImp();
    private LoggingUtil loggingUtil = new LoggingUtil();

    //Get all accounts and return them in a List.
    public List<Account> getAllAccounts(){
        loggingUtil.logService("AccountService", "Get All Accounts");
        return accountDao.getAllAccounts();
    }


    //Delete an account
    public boolean  deleteAccount(int id){
        if(accountDao.deleteAccount(id)){
            return true;
        }
        return false;
    }

    //Open an account
    public boolean openAccount(int id){
        if(accountDao.openAccount(id)){
            return true;
        }
        return false;
    }

    public boolean closeAccount(int id){
        if(accountDao.closeAccount(id)){
            return true;
        }
        return false;
    }

    //Check if an account is open.
    public boolean checkIfAccountOpen(Account account){
        if(account.isOpenState()){
            return true;
        } else {
            throw new UnauthorizedResponse("This account is not yet approved.");
        }
    }

    //Create an account using the person provided. This creates an empty account using the person.id
    public boolean applyForAccount(Person person){
        return accountDao.applyForAccount(person);
    }

    //Get an account by its ID.
    public Account getAccountById(int id){
        loggingUtil.logService("AccountService", "Get Account By Id");
        return accountDao.getAccountById(id);
    }

//This method will be used to add to the current balance, and will return the new balance.
    public boolean addBalance(Account account){
        loggingUtil.logService("AccountService", "Add Balance to Account #" + account.getId() + ".");
        return accountDao.addBalance(account);
    }

    public boolean subtractBalance(Account account){
        loggingUtil.logService("AccountService", "Subtract Balance from an Account.");
        return accountDao.subtractBalance(account);
    }

}
