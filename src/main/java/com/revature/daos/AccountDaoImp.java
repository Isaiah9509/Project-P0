package com.revature.daos;

import com.revature.models.Account;
import com.revature.models.Person;
import com.revature.models.Type;
import com.revature.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDaoImp implements AccountDao {


    @Override
    public boolean openAccount(int id) {
        String sql = "update account set open = true where id = ?";
        try(Connection c = ConnectionUtil.startConnection();
            PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, id);

            int alteredRows = ps.executeUpdate();
            if(alteredRows == 1){
                return true;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkIfOpen(int id){
        String sql = "select open from account where id = ?";
        Account account = new Account();
        try(Connection c = ConnectionUtil.startConnection();
            PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                account.setOpenState(Boolean.parseBoolean(rs.getString(1)));
                boolean openState = account.isOpenState();
                if(openState){
                    return true;
                }

            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteAccount(int id) {
        String sql = "delete from account where id = ?";
        try(Connection c = ConnectionUtil.startConnection();
            PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, id);

            int alteredRows = ps.executeUpdate();
            if(alteredRows == 1){
                return true;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean closeAccount(int id) {
        String sql = "update account set open = false where id = ?";
        try(Connection c = ConnectionUtil.startConnection();
            PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, id);

            int alteredRows = ps.executeUpdate();
            if(alteredRows == 1){
                return true;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }


    @Override
    //Takes all accounts and associated people and returns them as a list.
    public List<Account> getAllAccounts() {
        String sql = "Select * from account left join person on person.id = account.owner_id";
        List<Account> accountList = new ArrayList<>();
        try(Connection c = ConnectionUtil.startConnection();
            Statement s = c.createStatement()) {
            ResultSet rs = s.executeQuery(sql);
            while(rs.next()){
                //Creates a person and account object and adds it to the account list until there is nothing returned
                //from rs.next();
                Person p = new Person();
                p.setId(rs.getInt("owner_id"));
                int typeOrdinal = rs.getInt("type");
                Type[] types = Type.values(); //["CUSTOMER, EMPLOYEEE, BANKER"]
                p.setType(types[typeOrdinal]);
                p.setFirstName(rs.getString("first"));
                p.setLastName(rs.getString("last"));
                p.setUsername("Hidden");
                p.setPassword("Hidden");

                Account account = new Account();
                account.setId(rs.getInt("id"));
                account.setOwner(p);
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
        String sql = "select * from account left join person on person.id = account.owner_id where account.id = ?";

        try(Connection c = ConnectionUtil.startConnection();
            PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                Person p = new Person();
                p.setId(rs.getInt("owner_id"));
                int typeOrdinal = rs.getInt("type");
                Type[] types = Type.values(); //["CUSTOMER, EMPLOYEEE, BANKER"]
                p.setType(types[typeOrdinal]);
                p.setFirstName(rs.getString("first"));
                p.setLastName(rs.getString("last"));
                p.setUsername(rs.getString("username"));
                p.setPassword("hidden");

                Account account = new Account();
                account.setId(rs.getInt(id));
                account.setOwner(p);
                account.setBalance(rs.getDouble("balance"));
                account.setOpenState(rs.getBoolean("open"));

                return account;
            }

        } catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean applyForAccount(Person person) {
        String sql = "insert into account (owner_id) values (?)";
        try(Connection c = ConnectionUtil.startConnection();
            PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, person.getId());

            int alteredRows = ps.executeUpdate();
            if(alteredRows == 1){
                return true;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return false;
    }

}
