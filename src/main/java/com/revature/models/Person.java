package com.revature.models;


//Every person will have a first and last name. They will not have an account since they will not have
//created or requested an account to be created yet.
public class Person {
    private int id;
    private Type type;
    private String firstName;
    private String lastName;
    private String username;
    private String password;

    public Person(Type type, String firstName, String lastName, String username, String password) {
        this.type = type;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    public Person() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return type + " ID: " + id + ", First Name: " + firstName + ", Last Name: " + lastName;
    }
}