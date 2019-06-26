package com.exception;

public class BookNotFoundException extends NullPointerException {
    public BookNotFoundException(String s) {
        super(s);
    }
}
