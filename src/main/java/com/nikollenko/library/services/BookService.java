package com.nikollenko.library.services;

import com.nikollenko.library.model.Book;
import java.util.List;

public interface BookService {

        Book addBook(Book book);
        Book updateBook(long id, Book book);
        boolean removeBook(long id);
        Book getBookById(long id);
        List<Book> getAllBook();

        boolean setFree(long id);  //if book exsists and is free sets book to bysy state and returns true
        boolean setByzy(long id);  //if book exsists and is byzy sets book to free state and returns true
}
