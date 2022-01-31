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


        Javalin app = Javalin.create().start();

        PersonHandler personHandler = new PersonHandler();
        AccountHandler accountHandler = new AccountHandler();


        //Getting all people in the database.
        app.get("/people", personHandler::handleGetAll);
        //Getting a person by their id.
        app.get("/people/{id}", personHandler::handleGetOne);
        //Creating a person
        app.post("/people", personHandler::handlePersonCreate);
        //Getting all accounts in the database.
        app.get("/account", accountHandler::handleGetAll);
        //Adding balance to an account.
        app.put("/account/{id}/deposit", accountHandler::handleAddBalance);
        app.put("/account/{id}/withdraw", accountHandler::handleSubtractBalance);

        app.exception(NumberFormatException.class, (e, ctx)->{
        ctx.status(400);
        ctx.result("The input you provided cannot be parsed to an int value");
    });


//        app.get("/people", ctx -> {
//            //Interact request
//            Person newPerson = ctx.bodyAsClass(Person.class);
//            boolean sucess = personService.createPerson(newPerson);
//
//            //Prepare success
//            if(sucess){
//                ctx.status(201);
//            } else {
//                ctx.status(400);
//            }
//        });

//        Person testUser = new Person(Type.CUSTOMER, "Isaiah", "Payne", "isaiah.payne", "default");
//        personDao.createPerson(testUser);


        //Beginning switch statement for menu system. It starts HERE
        }


    }