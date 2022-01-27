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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role", referencedColumnName = "role_id")
    private Role role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer", referencedColumnName = "customer_id")
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
