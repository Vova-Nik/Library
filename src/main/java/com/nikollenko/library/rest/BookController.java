package com.nikollenko.library.rest;

import com.nikollenko.library.model.Book;
import com.nikollenko.library.services.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class BookController {

    BookService bookService;
    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/book/add")
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @PutMapping("/book/update")
    public Book updateBook(@RequestParam int id, Book book) {
        return bookService.updateBook(id, book);
    }

    @GetMapping("/book/getbyid")
    public Book getbyid(@RequestParam int id) {
        return  bookService.getBookById(id);
    }

    @GetMapping("/book/getall")
    public List<Book> getall() {
       return bookService.getAllBook();
    }

    @DeleteMapping("/book/remove")
    public String remove(@RequestParam int id) {
        bookService.removeBook(id);
        return "Deleted ";
    }

    @DeleteMapping("/book/remall")
    public String removeAll() {
        List<Book> books = bookService.getAllBook();
        for (Book book: books
        ) {
            bookService.removeBook(book.getId());
        }
        return "Deleted All Books";
    }

    @GetMapping("/book/test")
    public String test() {
        return "BookController test request mapping ";
    }

}
