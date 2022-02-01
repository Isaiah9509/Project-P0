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

    private Javalin app = Javalin.create().routes(()->{
        path("people",()-> {
            get(personHandler::handleGetAll); //Manager or Employee
            post(personHandler::handlePersonCreate); //Manager or Employee
            path("{id}", ()->{
                get(personHandler::handleGetOne); //Manager or Employeee
            });
        });
        path("account", () -> {
            get(accountHandler::handleGetAll);
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
            });
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
