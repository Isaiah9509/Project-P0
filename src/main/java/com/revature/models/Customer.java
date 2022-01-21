package com.revature.models;

import java.util.List;
//The customer will be created when a Person requests an account. This will extend from the Person class.
//The customer will have accounts once assigned to them, listed under the "accounts" variable.
public class Customer extends Person{

    private boolean hasAccount = false;
    private String username;
    private String password;
    private List<Account> accounts;

//Generated generic getters and setters along with a Constructor.
    public Customer(boolean hasAccount, String username, String password, List<Account> accounts) {
        this.hasAccount = hasAccount;
        this.username = username;
        this.password = password;
        this.accounts = accounts;
    }

    public boolean isHasAccount() {
        return hasAccount;
    }

    public void setHasAccount(boolean hasAccount) {
        this.hasAccount = hasAccount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }


    @Override
    public String toString() {
        return "Customer{" +
                "hasAccount=" + hasAccount +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", accounts=" + accounts +
                '}';
    }
}


