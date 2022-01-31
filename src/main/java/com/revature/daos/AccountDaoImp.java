package com.revature.daos;

import com.revature.models.Account;
import com.revature.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDaoImp implements AccountDao {


    @Override
    public List<Account> getAllAccounts() {
        String sql = "Select * from account";
        List<Account> accountList = new ArrayList<>();

        try(Connection c = ConnectionUtil.startConnection();
            Statement s = c.createStatement()) {
            ResultSet rs = s.executeQuery(sql);

            while(rs.next()){
                Account account = new Account();
                account.setId(rs.getInt("id"));
                account.setOwner_id(rs.getInt("owner_id"));
                account.setBalance(rs.getDouble("balance"));
                account.setOpenState(rs.getBoolean("open"));
                accountList.add(account);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accountList;
    }


    //Creating a way to add balance to an account.
    @Override
    public boolean addBalance(Account account) {
        //String using the Update SQL statement
        String sql = "update account set balance = balance + ? where id = ?";

        //Using the account given, add the balance and ID.
        try(Connection c = ConnectionUtil.startConnection();
            PreparedStatement ps = c.prepareStatement(sql)){
            ps.setDouble(1, account.getBalance());
            ps.setInt(2, account.getId());

            int alteredRows = ps.executeUpdate();
            if(alteredRows == 1){
                return true;
            }
        } catch(SQLException e){
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean subtractBalance(Account account) {
        String sql = "update account set balance = balance - ? where id = ?";

        try(Connection c = ConnectionUtil.startConnection();
        PreparedStatement ps = c.prepareStatement(sql)){
            ps.setDouble(1, account.getBalance());
            ps.setInt(2, account.getId());

            int alteredRows = ps.executeUpdate();
            if(alteredRows == 1){
                return true;
            }
        } catch(SQLException e){
            e.printStackTrace();
        }

        return false;
    }

    public Account getAccountById(int id){
        String sql = "select * from account where id = ?";
        Account account = new Account();

        try(Connection c = ConnectionUtil.startConnection();
            PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                account.setId(rs.getInt("id"));
                account.setOwner_id(rs.getInt("owner_id"));
                account.setBalance(rs.getDouble("balance"));
                account.setOpenState(rs.getBoolean("open"));

                return account;
            }

        } catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }

}
