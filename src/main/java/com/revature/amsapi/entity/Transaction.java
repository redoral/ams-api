package com.revature.amsapi.entity;

import javax.persistence.*;

@Entity
@Table
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int transaction_id;
    int transaction_ref_num;
    String transaction_date;
    String transaction_type;
    String transaction_sub_type;
    Double current_balance;

    // Entity relationship
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="account_number", nullable=false)
    private Account account;

    // Constructors
    public Transaction(){}

    public Transaction(int transaction_id, int transaction_ref_num, String transaction_date, String transaction_type, String transaction_sub_type, Double current_balance, Account account) {
        this.transaction_id = transaction_id;
        this.transaction_ref_num = transaction_ref_num;
        this.transaction_date = transaction_date;
        this.transaction_type = transaction_type;
        this.transaction_sub_type = transaction_sub_type;
        this.current_balance = current_balance;
        this.account = account;
    }

    public Transaction(int transaction_ref_num, String transaction_date, String transaction_type, String transaction_sub_type, Double current_balance, Account account) {
        this.transaction_ref_num = transaction_ref_num;
        this.transaction_date = transaction_date;
        this.transaction_type = transaction_type;
        this.transaction_sub_type = transaction_sub_type;
        this.current_balance = current_balance;
        this.account = account;
    }

    // Getters and setters
    public int getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(int transaction_id) {
        this.transaction_id = transaction_id;
    }

    public int getTransaction_ref_num() {
        return transaction_ref_num;
    }

    public void setTransaction_ref_num(int transaction_ref_num) {
        this.transaction_ref_num = transaction_ref_num;
    }

    public String getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(String transaction_date) {
        this.transaction_date = transaction_date;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }

    public String getTransaction_sub_type() {
        return transaction_sub_type;
    }

    public void setTransaction_sub_type(String transaction_sub_type) {
        this.transaction_sub_type = transaction_sub_type;
    }

    public Double getCurrent_balance() {
        return current_balance;
    }

    public void setCurrent_balance(Double current_balance) {
        this.current_balance = current_balance;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String toCSV(){
        return this.transaction_id + ", " +
                this.transaction_ref_num + ", " +
                this.transaction_date + ", " +
                this.transaction_type + ", " +
                this.transaction_sub_type + ", " +
                this.current_balance + ", ";
    }
}
