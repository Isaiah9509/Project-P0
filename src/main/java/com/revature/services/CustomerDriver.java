package com.revature.services;

import com.revature.models.Account;
import com.revature.models.Customer;

import java.util.Collections;
import java.util.Scanner;

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

        Scanner scn = new Scanner(System.in);
        //Creating a menu system loop for a placeholder
        boolean exit = false;
        String answer = new String();
        while(!exit){
        System.out.println("Basic menu system:");
        System.out.println("View Account \nAdd Balance\nSubtract Balance\nExit");
        answer = scn.nextLine();
        switch(answer){
            case "view account":
                break;
            case "add balance":
                break;
            case "subtract balance":
                break;
            case "exit":
                exit = true;
                break;
            default:
                System.out.println("Wrong selection, please try again");
        }



        }


        }


    }