package com.nikollenko.library.services;

import com.nikollenko.library.model.Book;
import com.nikollenko.library.services.exception.NoSuchBookException;

import java.util.List;

public interface BookService {

        Book addBook(Book book);
        Book updateBook(long id, Book book);
        boolean removeBook(long id) throws NoSuchBookException;
        Book getBookById(long id) throws NoSuchBookException;
        List<Book> getAllBook();

        boolean removeAllFree();

        boolean setFree(long id) throws NoSuchBookException;  //if book exsists and is free sets book to bysy state and returns true
        boolean setBuzy(long id) throws NoSuchBookException;  //if book exsists and is byzy sets book to free state and returns true
}
