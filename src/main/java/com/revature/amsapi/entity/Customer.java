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

    public int getPan() {
        return pan;
    }

    public void setPan(int pan) {
        this.pan = pan;
    }

    public int getCitizen_uid() {
        return citizen_uid;
    }

    public void setCitizen_uid(int citizen_uid) {
        this.citizen_uid = citizen_uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
