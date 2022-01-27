package com.revature.services;

import com.revature.models.Account;
import com.revature.models.Person;

import java.util.Scanner;

public class AccountService {

//This method will be used to add to the current balance, and will return the new balance.
    static Double addBalance(Account account, Double increment){
        return (account.getBalance() + increment);
    }

// Check if subtracting will be less than 0.
    static boolean canWithdraw(Double amount, Double subtraction){
        if(amount - subtraction < 0){
            return true;
        }
        System.out.println("You do not have a high enough balance to complete this transaction");
        return false;
    }

//This method will be used to subtract/withdraw from the current balance, and return the new balance.
    static Double subtractBalance(Account account, Double withdrawal) {
        //If there is a balance greater than 0 after you withdraw, return new balance amount.
        if(canWithdraw(account.getBalance(), withdrawal)) {
            return (account.getBalance() - withdrawal);
        } else {
            //Otherwise, return that you cannot withdraw more than you have in your balance.
            System.out.println("You cannot withdraw more than your balance");
        }
        //Returns this if the withdrawal was too much. (No change to balance)
        return account.getBalance();
    }

    //Will be used to essentially transfer the balance from one account to another. (Or any value add to another).
    static void transferBalance(Account account1, Account account2, Double transferAmount){
        //Check if account is open. Slight logic issue as if an account isn't open, there should be no money in the
        //account.
        if(!account1.isOpenState() || !account2.isOpenState()){
            System.out.println("Transfer could not be completed. One of these accounts has not been opened.");
        } else {
            //Runing the check to see if the account can be subtracted from.
            if(!canWithdraw(account1.getBalance(), transferAmount)){
                //If it can't, it'll give a prompt
                System.out.println("Not enough balance in Account " + account1.getId() + " for this transaction");
            } else {
                //If it passes that check, it will proceed with the transfer.
                account1.setBalance(account1.getBalance() - transferAmount);
                account2.setBalance(addBalance(account2, transferAmount));
            }

        }

    }

    //Need a way to login a user.  -- I have no implemented error checks
    //Logic is flawed here. Won't know the user until they attempt to login. Will need to pull this info from the
    //Database using a query, but for now, this will hold as an example.

}
