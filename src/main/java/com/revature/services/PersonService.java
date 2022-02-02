package com.revature.services;

import com.revature.daos.PersonDao;
import com.revature.daos.PersonDaoImp;
import com.revature.models.Person;
import com.revature.models.Type;

import java.util.List;
//The person service is responsible for using the PersonDao and Person Models to provide
//the ability to access and alter the database.
public class PersonService {

    private PersonDao personDao = new PersonDaoImp();

    //Create person method with different arguments to allow a person to register.
    public Person createPerson(String firstname, String lastname, String username, String password){
        Person p = new Person();
        p.setUsername(username);
        p.setPassword(password);
        p.setFirstName(firstname);
        p.setLastName(lastname);
        p.setType(Type.CUSTOMER);
        return p;
    }

    public Person getPersonByUser(String user){
        return personDao.getPersonByUser(user);
    }

    public Person getPersonByUserAndPass(String user, String pass){
        return personDao.getPersonByUserAndPass(user, pass);
    }

    public boolean createPerson(Person p){
        return personDao.createPerson(p);
    }

    public boolean updatePerson(Person p){
        return personDao.createPerson(p);
    }

    public Person getPersonById(int id){
        return personDao.getPersonById(id);
    }

    public List<Person> getAllPeople(){
        return personDao.getAllPerson();
    }

}
