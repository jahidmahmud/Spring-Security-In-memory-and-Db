package com.auth.main.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @GetMapping("/")
    public String getAll(){
        return "all the employees";
    }
    @GetMapping("/profile")
    public String getProfile(){
        return "all employees profile";
    }


}
