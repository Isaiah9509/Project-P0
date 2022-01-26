package com.revature.models;

//The employee will have an account and will have the ability to open accounts for the customer.
//They will not have any accounts themselves, only the ability to "approve" and view all other accounts.
public class Employee extends Person {

    //Initializing Constructor
    public Employee(String firstName, String lastName, String username, String password, boolean loggedIn) {
        super(firstName, lastName, username, password, loggedIn);
    }
}