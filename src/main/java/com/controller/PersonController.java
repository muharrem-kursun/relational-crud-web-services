package com.controller;

import com.model.PersonModel;

public interface PersonController {
    public void personSelectController();
    public void personDeleteController(int personId);
    public void personUpdateController(PersonModel personModel);
    public void personInsertController(PersonModel personModel);
}
