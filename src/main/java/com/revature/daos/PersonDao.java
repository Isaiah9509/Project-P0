package com.revature.daos;

import com.revature.models.Person;

import java.util.List;

public interface PersonDao {

    public Person getPersonByUserAndPass(String user, String pass);

    public boolean createPerson(Person p);

    public Person getPersonById(int id);

    public List<Person> getAllPerson();
}
