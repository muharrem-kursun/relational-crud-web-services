package com.service;

import com.dao.PersonDaoImpl;
import com.model.BookModel;
import com.model.PersonModel;

public class PersonServiceImpl implements PersonService {
    PersonDaoImpl personDaoImpl= new PersonDaoImpl();
    public void personSelectService() {
        personDaoImpl.personSelectDao();
    }

    public void personDeleteService(int personId) {
        personDaoImpl.personDeleteDao(personId);
    }

    public void personUpdateService(PersonModel personModel) {
        personDaoImpl.personUpdateDao(personModel);
    }

    public void personInsertService(PersonModel personModel) {
        personDaoImpl.personInsertDao(personModel);
    }
}

