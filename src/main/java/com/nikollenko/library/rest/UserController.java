package com.nikollenko.library.rest;

import com.nikollenko.library.model.User;
import com.nikollenko.library.services.UserService;
import com.nikollenko.library.services.exception.NoSuchUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin

public class UserController {

    private UserService userService;

    @Autowired
    public void setService(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/user/add")
    public User addClient(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PutMapping("/user/update")
    public User updateClient(@RequestParam User user, @RequestParam int id ) {
        try {
            return userService.updateUser(id, user);
        }catch(NoSuchElementException | NoSuchUserException e) {
            return new User();
        }
    }

    @DeleteMapping("/user/remove")
    public String removeClient(@RequestParam int id){
        try {
            userService.removeUser(id);
            return "User " + id + "deleted";
        }catch(NoSuchUserException e){
            return e.getMessage();
        }
    }

    @DeleteMapping("/user/remAll")
    public String remAll(){
        userService.removeAll();
        return "All users removed from DB";
    }

    @GetMapping("/user/getbyid")
    public User getUserById(@RequestParam int id) {
        try {
            return userService.getUserById(id);
        }catch(NoSuchUserException e){
            return new User();
        }
    }

    @GetMapping("/user/getall")
    public List<User> getAll() {
        return userService.getAllUsers();
    }

    @GetMapping("/user/test")
    public String userTest() {
        return "User controller test";
    }
}
