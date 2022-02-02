package com.revature.amsapi.controller;

import com.revature.amsapi.entity.Transaction;
import com.revature.amsapi.exception.InvalidInputException;
import com.revature.amsapi.exception.TransactionNotFoundException;
import com.revature.amsapi.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/transactions")
@CrossOrigin
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService){ this.transactionService = transactionService; }

    @GetMapping
    public List<Transaction> getTransactions(){
        return transactionService.getTransactions();
    }

    @GetMapping(path = "{transactionId}")
    public Transaction getTransactionById(@PathVariable("transactionId") Integer transactionId) throws TransactionNotFoundException { return transactionService.getTransaction(transactionId); }

    @GetMapping(path="account/{accountNumber}")
    public List<Transaction> getTransactionsByAccount(@PathVariable("accountNumber") Long accountNumber){ return transactionService.getTransactionsByAccount(accountNumber); }

    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction) throws TransactionNotFoundException, InvalidInputException { return transactionService.createTransaction(transaction); }

    @DeleteMapping(path = "{transactionId}")
    public boolean deleteTransaction(@PathVariable("transactionId") Integer transactionId) throws TransactionNotFoundException { return transactionService.deleteTransaction(transactionId); }
}
