package com.revature.daos;

import com.revature.models.Person;
import com.revature.models.Type;
import com.revature.utils.ConnectionUtil;
import com.revature.utils.LoggingUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PersonDaoImp implements PersonDao {

    private LoggingUtil loggingUtil = new LoggingUtil();

    @Override
    public Person getPersonByUserAndPass(String user, String pass) {
        String sql = "select * from person where username = ? and password = ?";
        try(Connection c = ConnectionUtil.startConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, user);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Person person = new Person();
                person.setId(rs.getInt("id"));
                int typeOrdinal = rs.getInt("type");
                Type[] types = Type.values(); //["CUSTOMER, EMPLOYEEE, BANKER"]
                person.setType(types[typeOrdinal]);
                person.setFirstName(rs.getString("first"));
                person.setLastName(rs.getString("last"));
                person.setUsername(rs.getString("username"));
                person.setPassword(rs.getString("password"));
                loggingUtil.logService(sql, (person.getUsername() + " " + person.getPassword()));
                return person;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Person getPersonByUser(String user) {
        String sql = "select * from person where username = ?";
        try(Connection c = ConnectionUtil.startConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, user);

            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Person person = new Person();
                person.setId(rs.getInt("id"));
                int typeOrdinal = rs.getInt("type");
                Type[] types = Type.values(); //["CUSTOMER, EMPLOYEEE, BANKER"]
                person.setType(types[typeOrdinal]);
                person.setFirstName(rs.getString("first"));
                person.setLastName(rs.getString("last"));
                person.setUsername(rs.getString("username"));
                person.setPassword("hidden");
                return person;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

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
            int alteredRows = ps.executeUpdate();
            if(alteredRows == 1){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Person getPersonById(int id) {
        Person p = new Person();
        String sql = "select * from person where id = ?";
        try(
        Connection c = ConnectionUtil.startConnection();
        PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                p.setId(rs.getInt("id"));
                int typeOrdinal = rs.getInt("type");
                Type[] types = Type.values(); //["CUSTOMER, EMPLOYEEE, BANKER"]
                p.setType(types[typeOrdinal]);
                p.setFirstName(rs.getString("first"));
                p.setLastName(rs.getString("last"));
                p.setUsername(rs.getString("username"));
                p.setPassword(rs.getString("password"));
            }
            } catch(SQLException e){
            e.printStackTrace();
        }
        return p;
    }

    @Override
    public List<Person> getAllPerson() {
        List<Person> people = new ArrayList<>();
        String sql = "select * from person";
        try(Connection c = ConnectionUtil.startConnection();
            Statement s = c.createStatement()) {
            ResultSet rs = s.executeQuery(sql);
            while(rs.next()){
                Person person = new Person();
                person.setId(rs.getInt("id"));
                int typeOrdinal = rs.getInt("type");
                Type[] types = Type.values(); //["CUSTOMER, EMPLOYEEE, BANKER"]
                person.setType(types[typeOrdinal]);
                person.setFirstName(rs.getString("first"));
                person.setLastName(rs.getString("last"));
                person.setUsername(rs.getString("username"));
                person.setPassword(rs.getString("password"));
                people.add(person);
                }

            } catch (SQLException e) {
            e.printStackTrace();
        }
        return people;
    }


}
