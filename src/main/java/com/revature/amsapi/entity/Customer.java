package com.revature.amsapi.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int customer_id;
    int pan;
    int citizen_uid;
    String name;
    String address;
    String email;
    String dob;

    // Entity relationship
    @OneToOne(mappedBy = "customer")
    private Users user;

    @OneToMany(mappedBy = "customer")
    private List<Account> account;

    // Constructors
    public Customer(){}

    public Customer(int customer_id, int pan, int citizen_uid, String name, String address, String email, String dob) {
        this.customer_id = customer_id;
        this.pan = pan;
        this.citizen_uid = citizen_uid;
        this.name = name;
        this.address = address;
        this.email = email;
        this.dob = dob;
    }

    public Customer(int pan, int citizen_uid, String name, String address, String email, String dob) {
        this.pan = pan;
        this.citizen_uid = citizen_uid;
        this.name = name;
        this.address = address;
        this.email = email;
        this.dob = dob;
    }
}
