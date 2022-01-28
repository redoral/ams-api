package com.revature.amsapi.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long account_number;
    double current_balance;

    // Entity relationship
    @ManyToOne
    @JoinColumn(name="customer_id", nullable=false)
    private Customer customer;

    @OneToMany(mappedBy = "account")
    private List<Transaction> transaction;

    // Constructors
    public Account(){}

    public Account(Long account_number, double current_balance) {
        this.account_number = account_number;
        this.current_balance = current_balance;
    }

    public Account(double current_balance) {
        this.current_balance = current_balance;
    }

    public Long getAccount_number() {
        return account_number;
    }

    public void setAccount_number(Long account_number) {
        this.account_number = account_number;
    }

    public double getCurrent_balance() {
        return current_balance;
    }

    public void setCurrent_balance(double current_balance) {
        this.current_balance = current_balance;
    }
}
