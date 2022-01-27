package com.revature.amsapi.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int transaction_id;
    int transaction_ref_num;
    Date transaction_date;
    String transaction_type;
    String transaction_sub_type;
    Double current_balance;

    // Entity relationship
    @ManyToOne
    @JoinColumn(name="account_number", nullable=false)
    private Account account;


    // Constructors
    public Transaction(){}

    public Transaction(int transaction_id, int transaction_ref_num, Date transaction_date, String transaction_type, String transaction_sub_type, Double current_balance, int account_number) {
        this.transaction_id = transaction_id;
        this.transaction_ref_num = transaction_ref_num;
        this.transaction_date = transaction_date;
        this.transaction_type = transaction_type;
        this.transaction_sub_type = transaction_sub_type;
        this.current_balance = current_balance;
    }

    public Transaction(int transaction_ref_num, Date transaction_date, String transaction_type, String transaction_sub_type, Double current_balance, int account_number) {
        this.transaction_ref_num = transaction_ref_num;
        this.transaction_date = transaction_date;
        this.transaction_type = transaction_type;
        this.transaction_sub_type = transaction_sub_type;
        this.current_balance = current_balance;
    }
}
