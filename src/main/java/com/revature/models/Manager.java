package com.revature.models;

public class Manager extends Person{  //Not sure if I'm going to use this yet.

    private String username;
    private String password;

    public Manager(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
