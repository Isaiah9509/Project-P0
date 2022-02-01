package com.revature.models;

//Account which will be used to store the account info and balance. It also has a boolean value used to determine
//If the account is currently open. (This will be modified by the Manager/Employee once the account is requested.
public class Account{
    private int id;
    private Person owner;
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

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
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
                ", owner=" + owner +
                ", balance=" + balance +
                ", openState=" + openState +
                '}';
    }
}
