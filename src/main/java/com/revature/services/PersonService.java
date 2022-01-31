package com.revature.services;

import com.revature.daos.PersonDao;
import com.revature.daos.PersonDaoImp;
import com.revature.models.Person;

import java.util.List;
//The person service is responsible for using the PersonDao and Person Models to provide
//the ability to access and alter the database.
public class PersonService {

    private PersonDao personDao = new PersonDaoImp();

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
