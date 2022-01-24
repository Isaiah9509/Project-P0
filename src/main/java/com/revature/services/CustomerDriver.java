package com.revature.services;

import com.revature.models.Account;
import com.revature.models.Customer;

import java.util.Collections;

public class CustomerDriver {

    //Need to login with username and password
    //Need to see accounts available under account.
    //Need to be able to transfer between accounts, as well as add and withdraw from them.
    //So in all, we need a username and password, and the ability to see the accounts stored under that username.

    public static void main(String[] args){
        //Testing to see if accounts are working with arraylist.
        //Creating a testCustomer with no account.
        Customer testCustomer = new Customer("Isaiah", "Payne", "isaiah230", "payne230", false, null);
        Account testAccount = new Account(1, 240, true);

        testCustomer.setAccounts(Collections.singletonList(testAccount));
        testCustomer.setHasAccount(true);

        System.out.println(testCustomer.getFirstName());

        System.out.println(testAccount.toString());
        System.out.println(testCustomer.toString());


        //Creating a loop to run a program for creating/accessing account.
//        boolean active = true;
//        System.out.println("Hello, welcome to the bank of Revature!");
//        while(active){
//            //I'm going to worry about Account creation later as I would like to see further into how we will
//            //be applying these accounts to databases we have stored. I can do this locally. I'm going to start small
//            //and work on getting a system set-up for adding/withrdawing and work from there.
//            int selection = 0;
//            //Creating a switch case to select to view accounts/select accounts.
//            System.out.println("Please select a menu option.");
//            System.out.println("1:Deposit into your account.");
//            System.out.println("2:Withdraw from your account.");
//            Scanner scanner = new Scanner(System.in);
//
//            selection = scanner.nextInt();
//            switch(selection) {
//                case 1:
//                    System.out.println("Where would you like to make your deposit?");
//                    break;
//                case 2:
//                    System.out.println("Where would you like to take your withdrawal from?");
//                    break;
//                default:
//                    return;
//
//            }
//
//            }


        }


    }