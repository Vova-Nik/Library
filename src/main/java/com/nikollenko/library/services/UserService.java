package com.nikollenko.library.services;

import com.nikollenko.library.model.User;
import com.nikollenko.library.services.exception.NoSuchBookException;
import com.nikollenko.library.services.exception.NoSuchUserException;

import java.util.List;

public interface UserService {

    User addUser(User user);

    User updateUser(long id, User user) throws NoSuchUserException;

    boolean removeUser(long id) throws NoSuchUserException;

    boolean removeAll();

    User getUserById (long id) throws NoSuchUserException;

    List<User> getAllUsers();

    boolean takeBook(long  usertId, long bookId) throws NoSuchBookException, NoSuchUserException;

    boolean returnBook(long  usertId, long bookId) throws NoSuchBookException, NoSuchUserException;
}
