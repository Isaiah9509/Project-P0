package com.revature.models;

//Account which will be used to store the account info and balance. It also has a boolean value used to determine
//If the account is currently open. (This will be modified by the Manager/Employee once the account is requested.
public class Account{
    private int id;
    private int owner_id;
    private double balance;
    private boolean openState = false;

    public Account(){
    }
    public Account(double balance, boolean openState) {
        this.balance = balance;
        this.openState = openState;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
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
                ", owner=" + owner_id +
                ", balance=" + balance +
                ", openState=" + openState +
                '}';
    }
}
