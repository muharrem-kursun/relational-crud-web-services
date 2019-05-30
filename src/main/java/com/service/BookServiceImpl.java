package com.service;

import com.dao.BookDaoImlp;
import com.model.Book;

public class BookServiceImpl implements BookService{
    BookDaoImlp bookDaoImlp;
    public BookServiceImpl() {
         bookDaoImlp = new BookDaoImlp();
    }



    public void getBook() {
            bookDaoImlp.getBooks();
    }

    public void addBook(Book book) {

        bookDaoImlp.addBook(book);
    }

    public void updateBook(Book book) {
        bookDaoImlp.updateBook(book);
    }

    public void removeBook(int bookId) {
        bookDaoImlp.removeBooks(bookId);
    }

    public void getJoinBook() {
        bookDaoImlp.getJoinBook();
    }
}
