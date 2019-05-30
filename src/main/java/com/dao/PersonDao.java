package com.dao;

import com.model.PersonModel;

public interface PersonDao {

    public void personSelectDao();
    public void personDeleteDao(int personId);
    public void personUpdateDao(PersonModel personModel);
    public void personInsertDao(PersonModel personModel);
}
