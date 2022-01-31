package com.revature;

import com.revature.handlers.AccountHandler;
import com.revature.handlers.PersonHandler;
import com.revature.utils.ConnectionUtil;
import io.javalin.Javalin;

import java.sql.Connection;

import static io.javalin.apibuilder.ApiBuilder.*;

public class JavalinApp {
    private PersonHandler personHandler = new PersonHandler();
    private AccountHandler accountHandler = new AccountHandler();

    private Javalin app = Javalin.create().routes(()->{
        path("people",()-> {
            get(personHandler::handleGetAll);
            post(personHandler::handlePersonCreate);
            path("{id}", ()->{
                get(personHandler::handleGetOne);
            });
        });
        path("account", () ->{
            put(accountHandler::handleGetAll);
            path("account/{id}", () ->{
                post(accountHandler::handleGetById);
            });
        });
        path("account/{id}/withdraw", () ->{
            put(accountHandler::handleSubtractBalance);
        });
        path("account/{id}/deposit", () ->{
            put(accountHandler::handleAddBalance);
        });
    });

}
