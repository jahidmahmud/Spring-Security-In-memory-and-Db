package com.auth.main.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_tbl")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String username;
    private String password;
    private boolean active;
    private String roles;

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isActive() {
        return active;
    }

    public String getRoles() {
        return roles;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public User(int id, String username, String password, boolean active, String roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.active = active;
        this.roles = roles;
    }

    public User() {
    }
}
