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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
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
