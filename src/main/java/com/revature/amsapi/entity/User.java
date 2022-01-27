package com.revature.amsapi.entity;

import javax.persistence.*;

@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int user_id;
    String password;

    // Entity relationship
    @OneToOne(mappedBy = "user")
    private Role role;

    @OneToOne(mappedBy = "user")
    private Customer customer;

    // Constructors
    public User(){}

    public User(int user_id, String password, int role_id, int customer_id, Role role, Customer customer) {
        this.user_id = user_id;
        this.password = password;
        this.role = role;
        this.customer = customer;
    }

    public User(String password, int role_id, int customer_id, Role role, Customer customer) {
        this.password = password;
        this.role = role;
        this.customer = customer;
    }
}
