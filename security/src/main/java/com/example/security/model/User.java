package com.example.security.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users_table")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String username;
    private String password;

    private boolean isAdmin;

    public User() {
    }

    public User(Long id, String username, String password, boolean isAdmin) {
        this.userId = id;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}