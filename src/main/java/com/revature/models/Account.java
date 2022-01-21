package com.revature.models;

//Account which will be used to store the account info and balance. It also has a boolean value used to determine
//If the account is currently open. (This will be modified by the Manager/Employee once the account is requested.
public class Account {
    private int id;
    private Customer owner;
    private double balance;
    private boolean openState = false;

    //Generic constructor as well as getters and setters and a toString() method.
    public Account(int id, double balance, boolean openState) {
        this.id = id;
        this.balance = balance;
        this.openState = openState;
    }

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

    public boolean isOpenState() {
        return openState;
    }

    public void setOpenState(boolean openState) {
        this.openState = openState;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", balance=" + balance +
                ", openState=" + openState +
                '}';
    }
}
