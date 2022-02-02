package com.revature.handlers;

import com.revature.models.Person;
import com.revature.models.Type;
import com.revature.services.PersonService;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

import static java.lang.Integer.parseInt;

public class PersonHandler {
    PersonService personService = new PersonService();

    public void handleGetAll(Context ctx){
        List<Person> people = personService.getAllPeople();
        ctx.json(people);
    }

    public void handleRegisterPerson(Context ctx){
        String username = ctx.formParam("username");
        String password = ctx.formParam("password");
        String firstname = ctx.formParam("firstname");
        String lastname = ctx.formParam("lastname");
        if(username == null || password == null || firstname == null ||password == null){
            ctx.result("Not enough information provided. Please enter your firstname, lastname, username, and password.");
        }
        Person p = personService.createPerson(firstname, lastname, username, password);
        boolean success = personService.createPerson(p);
        if(success){
            ctx.status(200);
            ctx.result("Account created for " + firstname + " " + lastname);
        } else {
            ctx.status(400);
            ctx.result("Could not create an account.");
        }

    }

    public void handleGetOne(Context ctx){
        Person p = personService.getPersonById(parseInt(ctx.pathParam("id")));
        if(p.getFirstName() == null) {
            ctx.result("ID entered does not exist in the database");
            ctx.status(400);
        } else {
            ctx.status(203);
            ctx.json(p);
        }
    }

    public void handlePersonRegister(Context ctx){
        Person p = new Person();
        p.setType(Type.valueOf("CUSTOMER"));
        p.setFirstName(ctx.formParam("firstname"));
        p.setLastName(ctx.formParam("lastname"));
        p.setUsername(ctx.formParam("username"));
        p.setUsername(ctx.formParam("password"));
    }

    public void handlePersonCreate(Context ctx){
        Person p = ctx.bodyAsClass(Person.class);
        boolean success = personService.createPerson(p);
        if(success) {
            ctx.status(203);
            ctx.result("MISSION SUCCESSFUL");
        } else {
            ctx.status(400);
            ctx.result("MISSION FAILED, WE'LL GET THEM NEXT TIME");
        }
    }
}
