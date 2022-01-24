package com.revature.models;

public interface AccountAccess {

//This method will be used to add to the current balance, and will return the new balance.
    public static Double addBalance(Double balance, Double increment){
        return (balance + increment);
    }

//This method will be used to subtract/withdraw from the current balance, and return the new balance.
    public static Double subtractBalance(Double balance, Double withdrawal) {
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
    public static void transferBalance(Double account1, Double account2){

    }

}
