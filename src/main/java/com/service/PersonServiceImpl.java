package com.service;

import com.dao.PersonDaoImpl;
import com.model.Person;

public class PersonServiceImpl implements PersonService {
    PersonDaoImpl personDaoImpl;
    public PersonServiceImpl() {
        personDaoImpl= new PersonDaoImpl();
    }


    public void getPerson() {
        personDaoImpl.getPerson();
    }

    public void removePerson(int personId) {
        personDaoImpl.removePerson(personId);
    }

    public void updatePerson(Person person) {
        personDaoImpl.updatePerson(person);
    }

    public void insertPerson(Person person) {
        personDaoImpl.addPerson(person);
    }
}

