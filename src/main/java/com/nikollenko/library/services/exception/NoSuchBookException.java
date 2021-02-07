package com.nikollenko.library.services.exception;

public class NoSuchBookException extends Exception {
    public NoSuchBookException(long id) {
        super(String.format("Book with id=%s does not exist", id));
    }
}
