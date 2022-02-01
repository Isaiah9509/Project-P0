package com.revature;

import com.revature.daos.PersonDao;
import com.revature.daos.PersonDaoImp;
import com.revature.handlers.AccountHandler;
import com.revature.handlers.PersonHandler;
import com.revature.models.Account;
import com.revature.models.Person;
import com.revature.models.Type;
import com.revature.services.AccountService;
import com.revature.services.PersonService;
import io.javalin.Javalin;

import java.sql.*;
import java.util.List;

import static java.lang.Integer.parseInt;

public class BankDriver {

    //Need to login with username and password
    //Need to see accounts available under account.
    //Need to be able to transfer between accounts, as well as add and withdraw from them.
    //So in all, we need a username and password, and the ability to see the accounts stored under that username.

    public static void main(String[] args) throws SQLException {
        //Testing to see if accounts are working with arraylist.
        //Creating a testCustomer with no account.


        JavalinApp app = new JavalinApp();
        app.start(8080);


        }


    }