package com.revature.utils;

import io.javalin.http.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class LoggingUtil {
    //Date can be provided if needed to log a transaction.
    private Date date = new Date();
    private static Logger logger = LoggerFactory.getLogger(LoggingUtil.class);

    public void logRequest(Context ctx){
    logger.info(ctx.method() + " request made to: " + ctx.path());

    }

    public void logConnection(){
        logger.info("Database connection");
    }

    public void logService(String service, String reason){
        logger.info(date + " " + service + " - " + reason);
    }
}