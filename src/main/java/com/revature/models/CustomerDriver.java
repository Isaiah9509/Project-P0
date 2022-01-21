package com.revature.models;

import com.revature.accounts.Account;

import java.util.ArrayList;
import java.util.Scanner;

public class CustomerDriver {

    //Need to login with username and password
    //Need to see accounts available under account.
    //Need to be able to transfer between accounts, as well as add and withdraw from them.
    //So in all, we need a username and password, and the ability to see the accounts stored under that username.

    public static void main(String[] args){
        //Testing to see if accounts are working with arraylist. (they kinda do?)
        /*
        ArrayList<Account> userAccounts = new ArrayList<>();
        Account testAccount = new Account((int) Math.random(), 2450,true);
        userAccounts.add(testAccount);
        System.out.println(userAccounts);
         */
        ArrayList<Account> testAccount = new ArrayList<>();
        testAccount.add(new Account(1, 459.32, true));

        //Creating a loop to run a program for creating/accessing account.
        boolean active = true;
        System.out.println("Hello, welcome to the bank of Revature!");
        while(active){
            //I'm going to worry about Account creation later as I would like to see further into how we will
            //be applying these accounts to databases we have stored. I can do this locally. I'm going to start small
            //and work on getting a system set-up for adding/withrdawing and work from there.
            int selection = 0;
            //Creating a switch case to select to view accounts/select accounts.
            System.out.println("Please select a menu option.");
            System.out.println("1:Deposit into your account.");
            System.out.println("2:Withdraw from your account.");
            Scanner scanner = new Scanner(System.in);

            selection = scanner.nextInt();
            switch(selection) {
                case 1:
                    System.out.println("Where would you like to make your deposit?");
                    break;
                case 2:
                    break;
                default:
                    return;

            }

            }


        }


    }

