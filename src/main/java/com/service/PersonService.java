package com.service;


import com.model.Person;

public interface PersonService {

    public void getPerson();
    public void removePerson(int personId) throws Exception;
    public void updatePerson(Person person) throws Exception;
    public void insertPerson(Person bookModel) throws Exception;
}
