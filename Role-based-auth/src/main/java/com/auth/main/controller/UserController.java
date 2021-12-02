package com.auth.main.controller;

import com.auth.main.entity.User;
import com.auth.main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public static final String[] ADMIN_ACCESS={"ROLE_ADMIN","ROLE_MODERATOR"};
    public static final String[] MODERATOR_ACCESS={"ROLE_MODERATOR"};
    @Autowired
    private UserRepository repository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @PostMapping("/join")
    public String joinGrup(@RequestBody User user){
        user.setRoles("ROLE_USER");
        String encode = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encode);
        this.repository.save(user);
        return "User crated successfully";
    }
    @GetMapping("/access/{id}/{role}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR')")
    public String giveAccess(@RequestParam int id,@RequestParam String role, Principal principal){
        User u = repository.findById(id).get();
        List<String> activeRoles = getRole(principal);
        String newrole="";
        if(activeRoles.contains(role)){
            newrole=u.getRoles()+","+role;
            u.setRoles(newrole);
        }
        repository.save(u);
        return "Updated role successfully";
    }

    public User getLoggedInUser(Principal principal){
        return this.repository.findByUsername(principal.getName()).get();
    }
    public List<String> getRole(Principal principal){
        String roles = getLoggedInUser(principal).getRoles();
        List<String> collect = Arrays.stream(roles.split(",")).collect(Collectors.toList());
        if(collect.contains("ROLE_ADMIN")){
            return Arrays.stream(ADMIN_ACCESS).collect(Collectors.toList());
        }
        if(collect.contains("ROLE_MODERATOR")){
            return Arrays.stream(MODERATOR_ACCESS).collect(Collectors.toList());
        }
        return Collections.emptyList();

    }
    @GetMapping("/getall")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<User> get(){
        return repository.findAll();
    }
    @GetMapping("/test")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String getUserAccess(){
        return "user can access this";
    }

}
