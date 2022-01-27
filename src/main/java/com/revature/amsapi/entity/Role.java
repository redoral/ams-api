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
    private User user;

    // Constructors
    public Role(){ }

    public Role(int role_id, String name){
        this.role_id = role_id;
        this.name = name;
    }

    public Role(String name){
        this.name = name;
    }
}
