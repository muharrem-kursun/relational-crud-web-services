package com.dao;

import com.model.Person;

public interface PersonDao {

    public void getPerson();
    public void removePerson(int personId);
    public void updatePerson(Person person);
    public void addPerson(Person person);
}
