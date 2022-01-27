package com.revature.amsapi.entity;

import javax.persistence.*;

@Entity
@Table
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int user_id;
    String password;

    // Entity relationship
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    // Constructors
    public Users(){}

    public Users(int user_id, String password) {
        this.user_id = user_id;
        this.password = password;
    }

    public Users(String password) {
        this.password = password;
    }

    // Getters and setters
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}