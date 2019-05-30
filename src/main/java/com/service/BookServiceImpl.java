package com.service;

import com.dao.BookDaoImlp;
import com.model.BookModel;

public class BookServiceImpl implements BookService{
    BookDaoImlp bookDaoImlp = new BookDaoImlp();
    public void bookSelectService() {
            bookDaoImlp.bookSelectDao();
    }

    public void bookInsertService(BookModel bookModel) {

        bookDaoImlp.bookInsertDao(bookModel);
    }

    public void bookUpdateService(BookModel bookModel) {
        bookDaoImlp.bookUpdateDao(bookModel);
    }

    public void bookDeleteService(int bookId) {
        bookDaoImlp.bookDeleteDao(bookId);
    }

    public void bookSelectJoinService() {
        bookDaoImlp.bookSelectJoinDao();
    }
}
