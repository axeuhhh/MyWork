//package com.example.demo.entity;
//
//import jakarta.persistence.*;
//
//import java.util.List;
//
//@Entity
//public class User {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String username;
//    private String password;
//    private String role = "USER";
//
//    // One-to-Many relationship with RequestHistory
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<RequestHistory> requestHistories;
//
//    public String getUsername() {
//        return username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public String getRole() {
//        return role;
//    }
//
//    public void setPassword(String encode) {
//        password = encode;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    // Getters and Setters
//    // ...
//}