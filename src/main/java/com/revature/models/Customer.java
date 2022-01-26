package com.revature.models;

import java.util.List;
//The customer will be created when a Person requests an account. This will extend from the Person class.
//The customer will have accounts once assigned to them, listed under the "accounts" variable.
public class Customer extends Person{

    private boolean hasAccount = false;
    private List<Account> accounts;

    public Customer(String firstName, String lastName, String username, String password, boolean loggedIn, boolean hasAccount, List<Account> accounts) {
        super(firstName, lastName, username, password, loggedIn);
        this.hasAccount = hasAccount;
        this.accounts = accounts;
    }

    public boolean isHasAccount() {
        return hasAccount;
    }

    public void setHasAccount(boolean hasAccount) {
        this.hasAccount = hasAccount;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }


    @Override
    public String toString() {
        return "Customer: " + super.getFirstName() + " " + super.getLastName() +
                " - has an account? " + hasAccount +
                ".\nList of accounts owned: " + accounts +
                '}';
    }
}


