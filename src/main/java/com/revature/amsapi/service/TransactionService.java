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

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository){
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    public List<Transaction> getTransactions(){
        return transactionRepository.findAll();
    }

    public Transaction getTransaction(int transactionId) {
        return transactionRepository.findById(transactionId).orElseThrow(() -> new IllegalStateException("Transaction with ID: " + transactionId + " does not exist."));
    }

    public List<Transaction> getTransactionsByAccount(Long accountNumber){
        return transactionRepository.getTransactionsByAccount(accountNumber);
    }

    public Transaction createTransaction(Transaction transaction){
        Account account = accountRepository.findById(transaction.getAccount().getAccount_number()).orElseThrow(() -> new IllegalStateException("Fail"));

        double accountBalance = account.getCurrent_balance();
        double transactionBalance = transaction.getCurrent_balance();

        System.out.println(transaction.getTransaction_type());

        if (transaction.getTransaction_type().equals("Deposit")){
                account.setCurrent_balance(accountBalance + transactionBalance);
        } else if ((transaction.getTransaction_type().equals("Withdraw") && accountBalance > transactionBalance)
                || (transaction.getTransaction_type().equals("Transfer") && accountBalance > transactionBalance)){
                account.setCurrent_balance(accountBalance - transactionBalance);
        } else {
            throw new IllegalStateException("Fail.");
        }

        accountRepository.save(account);
        transaction.setAccount(account);
        return transactionRepository.save(transaction);
    }

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
