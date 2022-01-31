package com.revature.handlers;

import com.revature.models.Account;
import com.revature.services.AccountService;
import io.javalin.http.Context;

import java.util.List;

public class AccountHandler {

    AccountService accountService = new AccountService();

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
            ctx.result("You have created an account");
        }
    }

    public void handleAddBalance(Context ctx){
        int id = Integer.parseInt(ctx.pathParam("id"));
        Account account = ctx.bodyAsClass(Account.class);
        account.setId(id);
        boolean success = accountService.addBalance(account);
        if(success){
            ctx.result("You have deposited " + account.getBalance() + ", which brings your account to " + accountService.getAccountById(account.getId()).getBalance());
            ctx.status(200);
        } else {
            ctx.status(400);
        }
    }

    public void handleSubtractBalance(Context ctx){
        int id = Integer.parseInt(ctx.pathParam("id"));
        Account account = ctx.bodyAsClass(Account.class);
        account.setId(id);
        boolean success = accountService.subtractBalance(account);
        if(success){
            ctx.result("You have withdrew your amount of " + account.getBalance() + ", which brings your account to " + accountService.getAccountById(account.getId()).getBalance());
            ctx.status(200);
        } else {
            ctx.status(400);
        }
    }
}
