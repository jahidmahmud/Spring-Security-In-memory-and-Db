package com.security.main;

import com.security.main.models.User;
import com.security.main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringSecurityApplication implements CommandLineRunner {
    @Autowired
    private UserRepository repository;
    @Autowired
    private BCryptPasswordEncoder encoder;

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User user1=new User();
        user1.setUsername("rakib");
        user1.setEmail("rakib@gmail.com");
        user1.setPassword(encoder.encode("1234"));
        user1.setRole("ROLE_ADMIN");
        repository.save(user1);
        User user=new User();
        user.setUsername("hasan");
        user.setEmail("hasan@gmail.com");
        user.setPassword(encoder.encode("1234"));
        user.setRole("ROLE_NORMAL");
        repository.save(user);

    }
}
