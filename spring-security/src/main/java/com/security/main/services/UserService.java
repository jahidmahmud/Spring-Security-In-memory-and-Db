package com.security.main.services;

import com.security.main.models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    List<User> list=new ArrayList<>();

    public UserService() {
        list.add(new User("Rakib","1234","rakib@gmail.com","admin"));
        list.add(new User("Zaman","4321","zaman@gmail.com","admin"));
        list.add(new User("Oli","9876","oli@gmail.com","admin"));
    }

    public List<User> getAllUser(){
        return this.list;
    }

    public User getSingleUser(String username){
        return this.list.stream().filter(x->x.getUsername().equals(username)).findAny().orElse(null);
    }

    public User addUser(User user){
        this.list.add(user);
        return user;
    }
}
