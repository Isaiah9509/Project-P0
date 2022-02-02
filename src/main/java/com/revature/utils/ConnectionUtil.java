package com.revature.utils;

import java.sql.*;

public class ConnectionUtil {

    private static final LoggingUtil loggingUtil = new LoggingUtil();

    public static Connection startConnection() throws SQLException {
            String url = "jdbc:postgresql://104.198.133.244:5432/";
            String username = System.getenv("DB_USER");
            String password = System.getenv("DB_PASS");
            loggingUtil.logConnection();
        return DriverManager.getConnection(url, username, password);
    }
}
