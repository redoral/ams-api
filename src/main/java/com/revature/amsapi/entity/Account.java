package com.revature.amsapi.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int account_number;
    int customer_id;
    double current_balance;

    @ManyToOne
    @JoinColumn(name="customer_id", nullable=false)
    private Customer customer;

    @OneToMany(mappedBy = "account")
    private List<Transaction> transaction;

    public Account(){}

    public Account(int account_number, int customer_id, double current_balance) {
        this.account_number = account_number;
        this.customer_id = customer_id;
        this.current_balance = current_balance;
    }

    public Account(int customer_id, double current_balance) {
        this.customer_id = customer_id;
        this.current_balance = current_balance;
    }
}
