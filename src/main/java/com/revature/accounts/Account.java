package com.revature.accounts;


//Every account will have an ID, balance, and open state.
public class Account {

    private int id;
    private double balance;
    private boolean accountOpen = false;
    //Unsure where these will be stored in the future


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean isAccountOpen() {
        return accountOpen;
    }

    public void setAccountOpen(boolean accountOpen) {
        this.accountOpen = accountOpen;
    }

    public Account(int id, double balance, boolean accountOpen) {
        this.id = id;
        this.balance = balance;
        this.accountOpen = accountOpen;
    }

    @Override
    public String toString() {
        return "Account" + "ID:" + id + "\t" +
                "Balance: $" + balance + "\t" +
                "State: " + accountOpen;
    }
}
