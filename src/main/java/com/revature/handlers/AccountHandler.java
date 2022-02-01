package com.revature.handlers;

import com.revature.models.Account;
import com.revature.services.AccountService;
import io.javalin.http.Context;

import java.util.List;

public class AccountHandler {

    AccountService accountService = new AccountService();
    //Need to be able to transfer account.
    public void handleTransfer(Context ctx){
        //Account 1 is provided by the url
        Account account1 = accountService.getAccountById(Integer.parseInt(ctx.pathParam("id")));
        //If the user provided login user matches the account owner, continue.
        if(account1.getOwner().getUsername().equals(ctx.header("UserLogin"))){
            //Account 2 is provided by the body of the request.
            Account account2 = accountService.getAccountById(Integer.parseInt(ctx.formParam("account_id")));
            //If the account 1 and 2 both exist, continue.
            if (account1 != null || account2 != null) {
                //Set the balance of account one for the subtraction event.
                account1.setBalance(Double.parseDouble(ctx.formParam("transfer_amount")));
                //If the subtraction event is successful, continue.
                if(accountService.subtractBalance(account1)){
                    //Set the transfer amount for the addition event.
                    account2.setBalance(Double.parseDouble(ctx.formParam("transfer_amount")));
                    boolean success = accountService.addBalance(account2);
                    //Finally, if the addition is completed successfully, return the result.
                    if(success){
                        ctx.status(200);
                        ctx.result("You have successfully transferred "+ ctx.formParam("transfer_amount") + "from your first account to your second");
                    }
                }
            }
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
            ctx.result("Not enough balance for this transaction");
        }
    }
}
