package com.revature;

import com.revature.handlers.AccountHandler;
import com.revature.handlers.AuthHandler;
import com.revature.handlers.ExceptionHandler;
import com.revature.handlers.PersonHandler;
import com.revature.utils.ConnectionUtil;
import com.revature.utils.LoggingUtil;
import io.javalin.Javalin;
import org.postgresql.util.PSQLException;

import java.sql.Connection;

import static io.javalin.apibuilder.ApiBuilder.*;

public class JavalinApp {
    private PersonHandler personHandler = new PersonHandler();
    private AccountHandler accountHandler = new AccountHandler();
    private LoggingUtil loggingUtil = new LoggingUtil();
    private ExceptionHandler exceptionHandler = new ExceptionHandler();
    private AuthHandler authHandler = new AuthHandler();
// Javalin app used to create connections and handle paths.
    private Javalin app = Javalin.create().routes(()->{
        //People path, used to GET ALL PEOPLE, CREATE A PERSON, OR GET A PERSON BY ID.
        path("people",()-> {
            get(personHandler::handleGetAll); //Manager or Employee
            post(personHandler::handlePersonCreate); //Manager
            path("{id}", ()->{
                get(personHandler::handleGetOne); //Manager or Employee
            });
            before("*", authHandler::authorizeEmployeeToken);
        });
        //Account path, used to get all accounts, create an account, get an account by id, add, and withdraw balances.
        //
        path("account", () -> {
            get(accountHandler::handleGetAll);
            path("apply", () ->{
                post(accountHandler::handleApplyAccount);
            });
            path("{id}", () ->{
                get(accountHandler::handleGetById);
                path("withdraw", () ->{
                    put(accountHandler::handleSubtractBalance);
                });
                path("deposit", () ->{
                    put(accountHandler::handleAddBalance);
                });
                path("transfer", () ->{
                    put(accountHandler::handleTransfer);
                });
                path("open", () ->{
                    put(accountHandler::handleOpenAccount);
                });
                path("close", () ->{
                    put(accountHandler::handleCloseAccount);
                });
                before("open", authHandler::authorizeEmployeeToken);
                path("close", () ->{
                    delete(accountHandler::handleDeleteAccount);
                });
                before("close", authHandler::authorizeManagerToken);
            });
            before("*", authHandler::authorizeCustomerToken);
        });
        path("register", () -> {
            post(personHandler::handleRegisterPerson);
            System.out.println("Made it here");
        });
        path("login", () -> {
           post(authHandler::authenticateLogin);
        });
        before("*", loggingUtil::logRequest);
    }).exception(NumberFormatException.class, exceptionHandler::handleNumberFormatException);


    public void start(int port){
        app.start(port);
    }

}
