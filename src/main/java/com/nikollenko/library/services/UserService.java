package com.nikollenko.library.services;

import com.nikollenko.library.model.User;

import java.util.List;

public interface UserService {

    User addUser(User user);

    User updateUser(long id, User user);

    boolean removeUser(long id);

    User getUserById (long id);

    List<User> getAllUsers();

    boolean takeBook(long  usertId, long bookId);

    boolean returnBook(long  usertId, long bookId);
}
