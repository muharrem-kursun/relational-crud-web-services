package com.service;


import com.model.Person;

public interface PersonService {

    public void getPerson();
    public void removePerson(int personId);
    public void updatePerson(Person person);
    public void insertPerson(Person bookModel);
}
