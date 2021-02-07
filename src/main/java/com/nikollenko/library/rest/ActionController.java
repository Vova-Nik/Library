package com.nikollenko.library.rest;

import com.nikollenko.library.services.BookService;
import com.nikollenko.library.services.UserService;
import com.nikollenko.library.services.exception.NoSuchBookException;
import com.nikollenko.library.services.exception.NoSuchUserException;
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
            try {
                userService.takeBook(userId, bookId);
                return "Book teken ";
            }catch(NoSuchBookException | NoSuchUserException e) {
                return e.getMessage();
            }
        }

    @PostMapping("/action/return")
    public String returnBook(@RequestParam int bookId, @RequestParam int userId) {
        try {
            userService.returnBook(userId, bookId);
            return "Return operation is successful.";
        }catch(NoSuchBookException | NoSuchUserException e) {
            return e.getMessage();
        }
    }

    @PostMapping("/action/test")
    public String actionTest(@RequestParam int bookId, @RequestParam int userId) {
        return "Test mapping. Successful." + bookId + " " + userId;
    }

}
