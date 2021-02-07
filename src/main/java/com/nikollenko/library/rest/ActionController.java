package com.nikollenko.library.rest;

import com.nikollenko.library.services.BookService;
import com.nikollenko.library.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class ActionController {

    BookService bookService;
    private UserService userService;

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/action/take")
    public String takeBook(@RequestParam int userId, @RequestParam int bookId) {
        if (userService.takeBook(userId, bookId)) {
            return userService.getUserById(userId).toString() + " take " +  bookService.getBookById(bookId).toString();
        }
        return "Not successful. Book is already taken or such User or Book are not exist.";
    }

    @PostMapping("/action/return")
    public String returnBook( @RequestParam int bookId, @RequestParam int userId) {
        if (userService.returnBook(userId, bookId)) {
            return userService.getUserById(userId).toString() + " returned "+ bookService.getBookById(bookId).toString();
        }
        return  "Return operation is not successful. User or book is faulty.";
    }

    @PostMapping("/action/test")
    public String actionTest(@RequestParam int bookId, @RequestParam int userId) {
        return "Test mapping. Successful." + bookId + " " +userId;
    }

}
