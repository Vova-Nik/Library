package com.nikollenko.library.rest;

import com.nikollenko.library.model.Book;
import com.nikollenko.library.services.BookService;

import com.nikollenko.library.services.exception.NoSuchBookException;
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
        try {
            return bookService.getBookById(id);
        }catch(NoSuchBookException e){
            return new Book();
        }
    }

    @GetMapping("/book/getall")
    public List<Book> getall() {
       return bookService.getAllBook();
    }

    @DeleteMapping("/book/remove")
    public String remove(@RequestParam int id) {
        try {
            bookService.removeBook(id);
            return "Deleted ";
        }catch(NoSuchBookException e) {
            return e.getMessage();
        }
    }

    @DeleteMapping("/book/remall")
    public String removeAll() {
        bookService.removeAllFree();
        return "Deleted All Books";
    }

    @GetMapping("/book/test")
    public String test() {
        return "BookController test request mapping ";
    }

}
