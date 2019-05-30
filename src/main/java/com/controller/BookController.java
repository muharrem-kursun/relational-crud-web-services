package com.controller;

import com.model.BookModel;

public interface BookController {
    public void bookSelectController();
    public void bookDeleteController(int bookId);
    public void bookUpdateController(BookModel bookModel);
    public void bookInsertController(BookModel bookModel);
    public void bookSelectJoincontroller();

}
