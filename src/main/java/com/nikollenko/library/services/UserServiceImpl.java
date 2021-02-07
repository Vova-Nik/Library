package com.nikollenko.library.services;

import com.nikollenko.library.dao.UserRepository;
import com.nikollenko.library.model.Book;
import com.nikollenko.library.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    @Autowired
    public void setRepo(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    BookService bookService;
    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    @Transactional
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User updateUser(long id, User user) throws NoSuchElementException {
        userRepository.findById(id);
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public boolean removeUser(long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public User getUserById(long id) throws NoSuchElementException {
        return userRepository.findById(id).orElse(new User());
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    @Transactional
    public boolean takeBook(long userId, long bookId) {
        if (!bookService.setByzy(bookId)) {
            return false;
        }
        try {
            User user = getUserById(userId);
            Book book = bookService.getBookById(bookId);
            if (user.takeBook(book)) {
                return true;
            }
            return false;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Override
    @Transactional
    public boolean returnBook(long clientId, long bookId) {
       if(! bookService.setFree(bookId)){
           return false;
       }
        try {
            User user = getUserById(clientId);
            Book book = bookService.getBookById(bookId);
            return user.returnBook(book);
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
