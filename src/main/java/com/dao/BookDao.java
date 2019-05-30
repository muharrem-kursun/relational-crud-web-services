package com.dao;

import com.model.BookModel;

public interface BookDao {

    public void bookSelectDao();
    public void bookDeleteDao(int bookID);
    public void bookUpdateDao(BookModel bookModel);
    public void bookInsertDao(BookModel bookModel);
}
