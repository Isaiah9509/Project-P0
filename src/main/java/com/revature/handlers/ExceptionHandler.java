package com.revature.handlers;

import io.javalin.http.Context;

public class ExceptionHandler {

    public void handleNumberFormatException(Exception e, Context ctx){
        ctx.status(400);
        ctx.result("The input you provided cannot be parsed ot an int value");
    }

    public void handleRequestNotFound(Context ctx){
        ctx.status(405);
        ctx.result("Request type not supported");
    }
}
