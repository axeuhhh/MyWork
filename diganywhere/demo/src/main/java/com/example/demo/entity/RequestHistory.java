//package com.example.demo.entity;
//
//import jakarta.persistence.*;
//
//@Entity
//public class RequestHistory {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String requestName;
//    private String equipmentAsked;
//    private String response;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public void setRequestName(String requestName) {
//        this.requestName = requestName;
//    }
//
//    public void setEquipmentAsked(String question) {
//        this.equipmentAsked = question;
//    }
//
//    public void setResponse(String response) {
//        this.response = response;
//    }
//
//    // Getters and Setters
//    // ...
//}