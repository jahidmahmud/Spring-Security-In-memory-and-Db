package com.auth.main.controller;

import com.auth.main.entity.User;
import com.auth.main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository repository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @GetMapping("/profile")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_EMP') or hasAuthority('ROLE_EXE')")
    public User getProfile(Principal principal){
        User user = this.repository.findByUsername(principal.getName()).get();
        return user;
    }
    @GetMapping("/count")
    public String getEmpCount(){
        long count = this.repository.findAll().stream().count();
        return "the count is : "+count;
    }
    @GetMapping("/")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_EXE')")
    public List<User> getAll(){
        return this.repository.findAll();
    }

    @PostMapping(path = "/add",consumes ="application/json")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User user1 =null;
        try {
            String encode = bCryptPasswordEncoder.encode(user.getPassword());
            user.setPassword(encode);
            user1 = repository.save(user);
            return ResponseEntity.ok().body(user1);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PutMapping(path = "/update",consumes ="application/json")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        try {
            User save = this.repository.save(user);
            return ResponseEntity.ok().body(save);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping(path = "/delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") String id,Principal principal){
        try {
            User byId = this.repository.getById(Integer.parseInt(id));
            User user = this.repository.findByUsername(principal.getName()).get();
            if(byId.getId()!=user.getId()){
                this.repository.delete(byId);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }


        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @GetMapping("/getall")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<User> get(){
        return repository.findAll();
    }
    @GetMapping("/test")
    @PreAuthorize("hasAuthority('ROLE_EMP')")
    public String getUserAccess(){
        return "user can access this";
    }

}
