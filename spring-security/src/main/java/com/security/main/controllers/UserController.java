package com.security.main.controllers;

import com.security.main.models.User;
import com.security.main.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping("/")
    public List<User> allUser(){
        return service.getAllUser();
    }
    @GetMapping("/{username}")
    public User getSingleUser(@PathVariable("username") String username){
        return service.getSingleUser(username);
    }
    @PostMapping("/")
    public User addUser(@RequestBody User user){
        return this.service.addUser(user);
    }
}
