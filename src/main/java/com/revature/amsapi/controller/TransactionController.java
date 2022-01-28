package com.revature.amsapi.controller;

import com.revature.amsapi.entity.Role;
import com.revature.amsapi.entity.Transaction;
import com.revature.amsapi.service.RoleService;
import com.revature.amsapi.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<Transaction> getTransactions(){
        return transactionService.getTransactions();
    }

    @GetMapping(path = "{transactionId}")
    public Transaction getTransactionById(@PathVariable("transactionId") Integer transactionId) {
        try {
            return transactionService.getTransaction(transactionId);
        } catch (IllegalStateException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction){
        return transactionService.createTransaction(transaction);
    }

    @DeleteMapping(path = "{transactionId}")
    public boolean deleteTransaction(@PathVariable("transactionId") Integer transactionId){
        try {
            transactionService.deleteTransaction(transactionId);
            return true;
        } catch(IllegalStateException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
