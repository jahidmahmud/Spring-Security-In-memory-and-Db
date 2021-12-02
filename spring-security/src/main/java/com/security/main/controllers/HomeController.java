package com.security.main.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class HomeController {
    @GetMapping("/home")
    //@PreAuthorize("hasRole('admin')")
    public String home(){
        return "i am from home";
    }

    @GetMapping("/login")
    public String login(){
        return "i am from login";
    }
    @GetMapping("/register")
    public String register(){
        return "i am from register";
    }
}
