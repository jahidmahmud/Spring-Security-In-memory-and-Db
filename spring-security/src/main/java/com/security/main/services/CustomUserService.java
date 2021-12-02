package com.security.main.services;

import com.security.main.models.CustomUserDetailsService;
import com.security.main.models.User;
import com.security.main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserService implements UserDetailsService {
    @Autowired
    private UserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User usr = this.repository.findByUsername(username);
        if(usr==null){
            throw new UsernameNotFoundException("no user");
        }
        return new CustomUserDetailsService(usr);
    }
}
