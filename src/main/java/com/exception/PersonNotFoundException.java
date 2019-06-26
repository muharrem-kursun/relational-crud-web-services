package com.exception;

public class PersonNotFoundException extends NullPointerException {
    public PersonNotFoundException(String message) {
        super(message);
    }
}
