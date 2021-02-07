package com.nikollenko.library.services;

import com.nikollenko.library.dao.UserRepository;
import com.nikollenko.library.model.Book;
import com.nikollenko.library.model.User;
import com.nikollenko.library.services.exception.NoSuchBookException;
import com.nikollenko.library.services.exception.NoSuchUserException;
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
    public User updateUser(long id, User user) throws NoSuchUserException {
        if (!userRepository.existsById(id)) {
            throw new NoSuchUserException(id);
        }
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public boolean removeUser(long id) throws NoSuchUserException {
        User user = userRepository.findById(id).orElseThrow(()->new NoSuchUserException(id));
        if(!user.getBooks().isEmpty()){
            return false;
        }
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean removeAll(){
        int i=0;
        List<User> users =  getAllUsers();
        for (User user :
                users) {
            try {
                removeUser(user.getId());
            }catch(NoSuchUserException e){
                i++;
            }
        }
        return true;
    }

    @Override
    @Transactional
    public User getUserById(long id) throws NoSuchUserException {
        return userRepository.findById(id).orElseThrow(()->new NoSuchUserException(id));
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    @Transactional
    public boolean takeBook(long userId, long bookId) throws NoSuchBookException, NoSuchUserException {
        User user = getUserById(userId);
        Book book = bookService.getBookById(bookId);
        if (!bookService.setBuzy(bookId)) {
            return false;
        }
        return user.takeBook(book);
    }

    @Override
    @Transactional
    public boolean returnBook(long clientId, long bookId) throws NoSuchBookException, NoSuchUserException {
        if (!bookService.setFree(bookId)) {
            return false;
        }
        User user = getUserById(clientId);
        Book book = bookService.getBookById(bookId);
        return user.returnBook(book);
    }
}
