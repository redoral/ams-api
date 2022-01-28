package com.revature.amsapi.entity;

import javax.persistence.*;

@Entity
@Table
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int role_id;
    String name;

    // Entity relationship
    @OneToOne(mappedBy = "role")
    private Users user;

    // Constructors
    public Role(){ }

    public Role(int role_id, String name){
        this.role_id = role_id;
        this.name = name;
    }

    public Role(String name){
        this.name = name;
    }

    // Getters and setters

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
