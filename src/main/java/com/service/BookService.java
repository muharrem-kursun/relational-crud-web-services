package com.service;

import com.model.Book;

public interface BookService {

    public void getBook();
    public void addBook(Book book);
    public void updateBook(Book book);
    public void removeBook(int bookId);
    public  void getJoinBook();
}
