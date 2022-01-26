package com.revature.models;

import java.util.Scanner;

public interface AccountAccess {

//This method will be used to add to the current balance, and will return the new balance.
    static Double addBalance(Double balance, Double increment){
        return (balance + increment);
    }

//This method will be used to subtract/withdraw from the current balance, and return the new balance.
    static Double subtractBalance(Double balance, Double withdrawal) {
        //If there is a balance greater than 0 after you withdraw, return new balance amount.
        if((balance - withdrawal) > 0) {
            return (balance - withdrawal);
        } else {
            //Otherwise, return that you cannot withdraw more than you have in your balance.
            System.out.println("You cannot withdraw more than your balance");
        }
        //Returns this if the withdrawal was too much. (No change to balance)
        return balance;
    }

    //Will be used to essentially transfer the balance from one account to another. (Or any value add to another).
    static void transferBalance(Double account1, Double account2){
    }

    //Need a way to login a user.  -- I have no implemented error checks
    static void loginAccount(Person person){
        while(!person.getLoggedIn()) {
            Scanner userScanner = new Scanner(System.in);
            System.out.println("Please enter your username and password.");
            System.out.println("Testing Purposes - Username and Password = " + person.getUsername() + " " + person.getPassword());
            System.out.println("Username:");
            String userInput = userScanner.nextLine();
            System.out.println("Password:");
            String passInput = userScanner.nextLine();
            if(userInput.equals(person.getUsername()) & passInput.equals(person.getPassword())){
                System.out.println("Successful Login, welcome " + person.getFirstName() + " " + person.getLastName() + ".");
                person.setLoggedIn(true);
            } else {
                System.out.println("Incorrect login, please try again");
            }
        }


    }

}
