package com.revature.daos;

import com.revature.models.Person;
import com.revature.utils.ConnectionUtil;

import java.sql.*;
import java.util.Collection;


public class PersonDaoImp implements PersonDao {

    private PreparedStatement preparePerson(Person person, PreparedStatement ps) throws SQLException {
        return null;
    }

    @Override
    public boolean createPerson(Person person) {
        String sql = "insert into person (type, first, last, username, password) values (?, ?, ?, ?, ?)";
        try(Connection c = ConnectionUtil.startConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, person.getType().ordinal());
            ps.setString(2, person.getFirstName());
            ps.setString(3, person.getLastName());
            ps.setString(4, person.getUsername());
            ps.setString(5, person.getPassword());
            System.out.println(ps);
            int alteredRows = ps.executeUpdate();
            if(alteredRows == 1){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }





}
