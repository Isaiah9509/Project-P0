package com.revature.handlers;

import com.revature.models.Person;
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
