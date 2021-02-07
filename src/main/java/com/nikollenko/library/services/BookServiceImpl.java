package com.nikollenko.library.services;

import com.nikollenko.library.dao.BookRepository;
import com.nikollenko.library.model.Book;

import com.nikollenko.library.services.exception.NoSuchBookException;
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
        if (bookRepository.existsById(id)) {
            return bookRepository.save(book);
        }
        return (new Book());
    }

    @Override
    @Transactional
    public boolean removeBook(long id) throws NoSuchBookException {
        try {
            Book book = getBookById(id);
            if (book.isBusy()) {
                return false;
            }
            bookRepository.deleteById(id);
            return true;
        } catch (NoSuchElementException e) {
            throw new NoSuchBookException(id);
        }
    }

    @Override
    @Transactional
    public boolean removeAllFree() {
        List<Book> toDelete = bookRepository.findAllByBusy(false);
        bookRepository.deleteAll(toDelete);
        return true;
    }

    @Override
    @Transactional
    public Book getBookById(long id) throws NoSuchBookException {
        return bookRepository.findById(id).orElseThrow(() -> new NoSuchBookException(id));
    }

    @Override
    @Transactional
    public List<Book> getAllBook() {
        return (List<Book>) bookRepository.findAll();
    }

    @Override
    @Transactional
    public boolean setBuzy(long id) throws NoSuchBookException {
            Book book = getBookById(id);
            if (book.isBusy()) {
                return false;
            }
            book.setBusy(true);
            updateBook(id, book);
            return true;
    }

    @Override
    @Transactional
    public boolean setFree(long id) throws NoSuchBookException {
            Book book = getBookById(id);
            book.setBusy(false);
            updateBook(id, book);
            return true;
    }
}
