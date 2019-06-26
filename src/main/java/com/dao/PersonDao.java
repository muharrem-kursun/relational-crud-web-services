package com.dao;

import com.model.Person;

public interface PersonDao {

    public void getPerson();
    public boolean getFindById(int id);
    public void removePerson(int personId) throws Exception;
    public void updatePerson(Person person) throws Exception;
    public void addPerson(Person person) throws Exception;
}
