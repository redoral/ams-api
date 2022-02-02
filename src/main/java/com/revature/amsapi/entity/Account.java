package com.revature.amsapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Account {

    @Id
    @SequenceGenerator(name="ACCOUNT_SEQUENCE_GENERATOR", sequenceName="ACCOUNT_SEQUENCE", initialValue=1782647590)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACCOUNT_SEQUENCE_GENERATOR")
    Long account_number;
    String account_type;
    double current_balance;

    // Entity relationship
    @ManyToOne
    @JoinColumn(name="customer_id", nullable=false)
    public Customer customer;

    @JsonIgnore
    @OneToMany(mappedBy = "account")
    private List<Transaction> transaction;

    // Constructors
    public Account(){}

    public Account(Long account_number, double current_balance, String account_type) {
        this.account_number = account_number;
        this.current_balance = current_balance;
        this.account_type = account_type;
    }

    public Account(double current_balance) {
        this.current_balance = current_balance;
    }

    // Getters and setters
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

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Transaction> getTransaction() {
        return transaction;
    }

    public void setTransaction(List<Transaction> transaction) {
        this.transaction = transaction;
    }
}
