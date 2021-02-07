package com.nikollenko.library.services;

import com.nikollenko.library.dao.BookRepository;
import com.nikollenko.library.model.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public Book updateBook(long id, Book book) throws NoSuchElementException {
        Book oldBook = bookRepository.findById(id).get();
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public boolean removeBook(long id) {
        if (bookRepository.existsById(id)) {
            if(bookRepository.findById(id).get().isBusy()) {
                return true;                                    //BYZY Book could not be deleted because of there exists reference in User
            }
                bookRepository.deleteById(id);
                return true;
        }
        return false;
    }

    @Override
    @Transactional
    public Book getBookById(long id) throws NoSuchElementException {
        return bookRepository.findById(id).get();
    }

    @Override
    @Transactional
    public List<Book> getAllBook() {
        return (List<Book>) bookRepository.findAll();
    }

    @Override
    @Transactional
    public boolean setByzy(long id) {
        try {
            Book book = getBookById(id);
            if (book.isBusy()) {
                return false;
            }
            book.setBusy(true);
            updateBook(id, book);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Override
    @Transactional
    public boolean setFree(long id) {
        try {
            Book book = getBookById(id);
            book.setBusy(false);
            updateBook(id, book);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
