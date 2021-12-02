package com.security.main.repository;

import com.security.main.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
    public User findByUsername(String username);
}
