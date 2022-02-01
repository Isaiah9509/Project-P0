package com.revature.handlers;

import com.revature.models.Person;
import com.revature.services.PersonService;
import io.javalin.http.Context;
import io.javalin.http.ForbiddenResponse;
import io.javalin.http.UnauthorizedResponse;

public class AuthHandler {

    private PersonService personService = new PersonService();

    public void authenticateLogin(Context ctx){
        String username = ctx.formParam("username");
        String password = ctx.formParam("password");

        Person person = personService.getPersonByUserAndPass(username, password);

        if(person==null){
            throw new UnauthorizedResponse("No user with these login credentials.");
        } else {
            //Need to replace this with REAL authentication later.
            String userLogin = person.getUsername();
            String simpleToken = person.getType()+"-TOKEN";
            ctx.header("Authorization", simpleToken);
            ctx.header("UserLogin", userLogin);
            ctx.status(200);
        }
    }

    //Employee Level Authorization
    public void authorizeEmployeeToken(Context ctx){
        String authHeader = ctx.header("Authorization");
        if(authHeader!=null){
            if(authHeader.equals("EMPLOYEE-TOKEN") || authHeader.equals("MANAGER-TOKEN")){
                return;
            } else if (authHeader.equals("CUSTOMER-TOKEN")){
                throw new ForbiddenResponse("Customers are unable to access this feature.");
            }
        }
        throw new UnauthorizedResponse("Please login");
    }

    public void authorizeManagerToken(Context ctx){
        String authHeader = ctx.header("Authorization");
        if(authHeader!=null){
            if(authHeader.equals("MANAGER-TOKEN")){
                return;
            } else if (authHeader.equals("CUSTOMER-TOKEN") || authHeader.equals("EMPLOYEE-TOKEN")){
                throw new ForbiddenResponse("You must be a manager to access this feature.");
            }
        }
        throw new UnauthorizedResponse("Please login");
    }

    public void authorizeCustomerToken(Context ctx){
        String authHeader = ctx.header("Authorization");
        if(authHeader!=null){
            if(authHeader.equals("EMPLOYEE-TOKEN") || authHeader.equals("MANAGER-TOKEN") || authHeader.equals("CUSTOMER-TOKEN")){
                return;
            }
        }
        throw new UnauthorizedResponse("Please login");
    }

}
