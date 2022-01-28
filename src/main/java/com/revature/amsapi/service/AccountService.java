package com.revature.amsapi.service;

import com.revature.amsapi.entity.Account;
import com.revature.amsapi.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    // Init repository to call queries
    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    // Returns all accounts
    public List<Account> getAccounts(){
        return accountRepository.findAll();
    }

    // Get an account by id
    public Account getAccount(Long accountId) {
        return accountRepository.findById(accountId).orElseThrow(() -> new IllegalStateException("Account with ID: " + accountId + " does not exist."));
    }

    // Creates a new account
    public Account createAccount(Account account){ return accountRepository.save(account); }

    // Deletes an account
    public boolean deleteAccount(Long accountId) {
        accountRepository.findById(accountId).orElseThrow(() -> new IllegalStateException("Account with ID: " + accountId + " does not exist."));

        try {
            accountRepository.deleteById(accountId);
        } catch (Exception e) {
            System.out.println("Error:" + e);
            return false;
        }

        return true;
    }

    // Updates an account
    @Transactional
    public Account updateAccount(Long accountId, Double newBalance) {
        Account updatedAccount = accountRepository.findById(accountId).orElseThrow(() ->
                new IllegalStateException("Customer with ID: " + accountId + " does not exist."));

        if (newBalance !=  null) {
            updatedAccount.setCurrent_balance(newBalance);
        }

        return accountRepository.save(updatedAccount);
    }
}
