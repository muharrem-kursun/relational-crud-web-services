package com.service;


import com.model.BookModel;
import com.model.PersonModel;

public interface PersonService {

    public void personSelectService();
    public void personDeleteService(int personId);
    public void personUpdateService(PersonModel personModel);
    public void personInsertService(PersonModel bookModel);
}
