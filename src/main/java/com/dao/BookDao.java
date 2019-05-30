package com.dao;

import com.model.Book;

public interface BookDao {

    public void getBooks();
    public void removeBooks(int bookID);
    public void updateBook(Book book);
    public void addBook(Book book);
    public void getJoinBook();
}
