package com.revature.amsapi.service;

import com.revature.amsapi.entity.Account;
import com.revature.amsapi.entity.Transaction;
import com.revature.amsapi.exception.AccountNotFoundException;
import com.revature.amsapi.exception.InvalidInputException;
import com.revature.amsapi.exception.TransactionNotFoundException;
import com.revature.amsapi.repository.AccountRepository;
import com.revature.amsapi.repository.TransactionRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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

    public Transaction getTransaction(int transactionId) throws TransactionNotFoundException {
        return transactionRepository.findById(transactionId).orElseThrow(() -> new TransactionNotFoundException("Transaction with ID: " + transactionId + " does not exist."));
    }

    public List<Transaction> getTransactionsByAccount(Long accountNumber) throws AccountNotFoundException {
        accountRepository.findById(accountNumber).orElseThrow(() -> new AccountNotFoundException(("Account with account number: " + accountNumber + " does not exist.")));
        return transactionRepository.getTransactionsByAccount(accountNumber);
    }

    public List<Transaction> getLast5TransactionsByAccount(Long accountNumber) throws AccountNotFoundException{
        accountRepository.findById(accountNumber).orElseThrow(() -> new AccountNotFoundException(("Account with account number: " + accountNumber + " does not exist.")));
        return transactionRepository.getTransactionsByAccount(accountNumber).stream().limit(5).collect(Collectors.toList());
    }

    public List<Transaction> getTransactionsByDateRange(Long accountNumber, String timestamp1, String timestamp2) throws AccountNotFoundException {
        accountRepository.findById(accountNumber).orElseThrow(() -> new AccountNotFoundException(("Account with account number: " + accountNumber + " does not exist.")));

        List<Transaction> transactions = transactionRepository.getTransactionsByAccount(accountNumber).stream()
                .filter(transaction ->
                        LocalDate.parse(transaction.getTransaction_date().substring(0, 10)).isAfter(LocalDate.parse(timestamp1.substring(0, 10))) &&
                                LocalDate.parse(transaction.getTransaction_date().substring(0, 10)).isBefore(LocalDate.parse(timestamp2.substring(0, 10)))).collect(Collectors.toList());

        return transactions;
    }

    public Transaction createTransaction(Transaction transaction) throws TransactionNotFoundException, InvalidInputException {
        Account account = accountRepository.findById(transaction.getAccount().getAccount_number()).orElseThrow(() -> new TransactionNotFoundException("Account with account number: " + transaction.getAccount().getAccount_number() + " does not exist."));

        double accountBalance = account.getCurrent_balance();
        double transactionBalance = transaction.getCurrent_balance();

        if (transaction.getTransaction_type().equals("Deposit")){
                account.setCurrent_balance(accountBalance + transactionBalance);
        } else if ((transaction.getTransaction_type().equals("Withdraw") && accountBalance > transactionBalance)
                    || (transaction.getTransaction_type().equals("Transfer") && accountBalance > transactionBalance)){
                account.setCurrent_balance(accountBalance - transactionBalance);
        } else {
            throw new InvalidInputException("Invalid transaction type: " + transaction.getTransaction_type());
        }

        accountRepository.save(account);
        transaction.setAccount(account);
        return transactionRepository.save(transaction);
    }

    public boolean deleteTransaction(int transactionId) throws TransactionNotFoundException {
        transactionRepository.findById(transactionId).orElseThrow(() -> new TransactionNotFoundException("Transaction with ID: " + transactionId + " does not exist."));
        transactionRepository.deleteById(transactionId);
        return true;
    }

}
