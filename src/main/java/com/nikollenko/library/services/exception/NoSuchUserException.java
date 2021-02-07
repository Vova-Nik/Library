package com.nikollenko.library.services.exception;

public class NoSuchUserException extends Exception {
    public NoSuchUserException(long id) {
        super(String.format("User with id=%s does not exist", id));
    }
}
