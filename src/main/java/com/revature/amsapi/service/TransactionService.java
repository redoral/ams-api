package com.revature.amsapi.service;

import com.revature.amsapi.entity.Account;
import com.revature.amsapi.entity.Transaction;
import com.revature.amsapi.repository.AccountRepository;
import com.revature.amsapi.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    // Init repository to call queries
    @Autowired
    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository){
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    // Returns all transactions
    public List<Transaction> getTransactions(){
        return transactionRepository.findAll();
    }

    // Get a transaction by id
    public Transaction getTransaction(int transactionId) {
        return transactionRepository.findById(transactionId).orElseThrow(() -> new IllegalStateException("Transaction with ID: " + transactionId + " does not exist."));
    }

    // Creates a new transaction
    public Transaction createTransaction(Transaction transaction){
        Account account = accountRepository.findById(transaction.getAccount().getAccount_number()).orElseThrow(() -> new IllegalStateException("Fail"));
        transaction.setAccount(account);
        return transactionRepository.save(transaction);
    }

    // Deletes a transaction
    public boolean deleteTransaction(int transactionId) {
        transactionRepository.findById(transactionId).orElseThrow(() -> new IllegalStateException("Transaction with ID: " + transactionId + " does not exist."));

        try {
            transactionRepository.deleteById(transactionId);
        } catch (Exception e) {
            System.out.println("Error:" + e);
            return false;
        }

        return true;
    }

}
