package com.service;

import com.model.BookModel;

public interface BookService {

    public void bookSelectService();
    public void bookInsertService(BookModel bookModel);
    public void bookUpdateService(BookModel bookModel);
    public void bookDeleteService(int bookId);
    public  void  bookSelectJoinService();
}
