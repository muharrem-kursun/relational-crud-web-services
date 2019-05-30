package com.model;

import javax.ws.rs.QueryParam;

public class BookModel {
    @QueryParam("bookId")
    private int bookId;
    @QueryParam("personId")
    private int personId;
    @QueryParam("bookName")
    private String bookName;
    @QueryParam("bookAuthor")
    private String bookAuthor;

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    @Override
    public String toString() {
        return "BookModelImpl{" +
                "bookId=" + bookId +
                ", personId=" + personId +
                ", bookName='" + bookName + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                '}';
    }
}
