package com.revature.handlers;

import com.revature.models.Account;
import com.revature.models.Person;
import com.revature.services.AccountService;
import com.revature.services.PersonService;
import io.javalin.http.Context;
import io.javalin.http.UnauthorizedResponse;

import java.util.List;

public class AccountHandler {

    private final PersonService personService = new PersonService();
    private final AccountService accountService = new AccountService();


    //Need handler for deleting an account
    public void handleDeleteAccount(Context ctx){
    if(accountService.deleteAccount(Integer.parseInt(ctx.pathParam("id")))){
        ctx.status(200);
        ctx.result("Account successfully deleted");
        } else {
        ctx.status(403);
        ctx.result("Account could not be deleted");
    }
    }

    //Need a handler for creating an account (Must be logged in for this feature)
    public void handleApplyAccount(Context ctx){
        if (!ctx.header("Authorization").equals("CUSTOMER-TOKEN")) {
                throw new UnauthorizedResponse("Please login to apply for an account");
            }
        Person person = personService.getPersonByUser(ctx.header("UserLogin"));
        if(person == null){
            throw new UnauthorizedResponse("Login not found, please try logging in again");
        } else {
            boolean success = accountService.applyForAccount(person);
            if(success) {
                ctx.result("An account has been opened for " + person.getFirstName() + " " + person.getLastName());
                ctx.status(200);
            } else {
                ctx.status(400);
                ctx.result("Account could not be opened.");
            }
        }
    }

    public void handleOpenAccount(Context ctx){
        int id = Integer.parseInt(ctx.pathParam("id"));
        Account account = accountService.getAccountById(id);
        if(account.isOpenState()){
            ctx.status(403);
            ctx.result("The account is already open.");
        }else {
            boolean success = accountService.openAccount(id);
            if (success) {
                ctx.status(200);
                ctx.result("The account has been opened");
            } else {
                ctx.status(403);
                ctx.result("The account could not be opened");
            }
        }
    }


    public void handleCloseAccount(Context ctx){
        int id = Integer.parseInt(ctx.pathParam("id"));
        Account account = accountService.getAccountById(id);
        boolean accountOpen = account.isOpenState();
        if(!accountOpen) {
            ctx.status(403);
            ctx.result("The account is already closed.");
        } else {
        boolean success = accountService.closeAccount(id);
        if(success){
            ctx.status(200);
            ctx.result("The account has been closed");
        } else {
            ctx.status(403);
            ctx.result("The account could not be closed");
        }
        }
    }

    //Need to be able to transfer account.
    public void handleTransfer(Context ctx) {
        //Providing local variables for repeating values in the function.
        double transferAmount = Double.parseDouble(ctx.formParam(   "transfer_amount"));
        //Account 1 is provided by the url
        Account account1 = accountService.getAccountById(Integer.parseInt(ctx.pathParam("id")));

        //Check if account is open.
        accountService.checkIfAccountOpen(account1);

        //Check to see if the level of the user is customer, and
        if (ctx.header("Authorization").equals("CUSTOMER-TOKEN")) {
            if(!account1.getOwner().getUsername().equals(ctx.header("UserLogin"))){
                throw new UnauthorizedResponse("You are not the owner of this account");
        }
        }
            //If the user provided login user matches the account owner, continue.
            if (account1.getOwner().getUsername().equals(ctx.header("UserLogin"))) {
                //Account 2 is provided by the body of the request.
                Account account2 = accountService.getAccountById(Integer.parseInt(ctx.formParam("account_id")));
                //If the account 1 and 2 both exist, continue.
                if (account1 != null && account2 != null) {
                    //Set the balance of account one for the subtraction event.
                    account1.setBalance(transferAmount);
                    //If the subtraction event is successful, continue.
                    if (accountService.subtractBalance(account1)) {
                        //Set the transfer amount for the addition event.
                        account2.setBalance(transferAmount);
                        boolean success = accountService.addBalance(account2);
                        //Finally, if the addition is completed successfully, return the result.
                        if (success) {
                            ctx.result("You have successfully transferred $" + transferAmount + " from your first account to the second account provided.");
                            ctx.status(200);
                        } else {
                            ctx.status(400);
                            ctx.result("Could not complete transaction");
                        }

                    } else {
                        ctx.status(400);
                        ctx.result("Not high enough balance to transfer this amount.");
                    }
                } else {
                    ctx.status(400);
                    ctx.result("The second account provided does not exist");
                }
            } else {
                throw new UnauthorizedResponse("You are not the owner of this account");
            }

        }

    public void handleGetAll(Context ctx){
        List<Account> accountList = accountService.getAllAccounts();
        ctx.json(accountList);
    }

    public void handleGetById(Context ctx){
        int id = Integer.parseInt(ctx.pathParam("id"));
        Account account = accountService.getAccountById(id);
        if (account == null){
            ctx.status(400);
            ctx.result("Account does not exist");
        } else {
            ctx.status(200);
            ctx.json(account);
        }
    }

    public void handleAddBalance(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Account account = ctx.bodyAsClass(Account.class);
        account.setId(id);
        //Check if the account is open. Throws exception if not.
        boolean open = accountService.checkIfAccountOpen(account);
        if (open == false) {
            ctx.status(400);
            ctx.result("Account is not open");
        } else {
            boolean success = accountService.addBalance(account);
            if (success) {
                ctx.result("You have deposited " + account.getBalance() + ", which brings your account to " + accountService.getAccountById(account.getId()).getBalance());
                ctx.status(200);
            } else {
                ctx.status(400);
            }
        }
    }

    public void handleSubtractBalance(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Account account = ctx.bodyAsClass(Account.class);
        account.setId(id);
        //Check if the account is open. Throws exception if not.
        boolean open = accountService.checkIfAccountOpen(account);
        if (!open) {
            ctx.status(400);
            ctx.result("Account is not open");
        } else {
            boolean success = accountService.subtractBalance(account);
            if (success) {
                ctx.result("You have withdrew your amount of " + account.getBalance() + ", which brings your account to " + accountService.getAccountById(account.getId()).getBalance());
                ctx.status(200);
            } else {
                ctx.status(400);
                ctx.result("Not enough balance for this transaction");
            }
        }
    }
}
