package com.revature.amsapi.service;

import com.revature.amsapi.entity.Account;
import com.revature.amsapi.entity.Customer;
import com.revature.amsapi.repository.AccountRepository;
import com.revature.amsapi.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    // Init repository to call queries
    @Autowired
    public AccountService(CustomerRepository customerRepository, AccountRepository accountRepository){
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

    // Returns all accounts
    public List<Account> getAccounts(){
        return accountRepository.findAll();
    }

    // Get an account by id
    public Account getAccount(Long accountId) {
        return accountRepository.findById(accountId).orElseThrow(() -> new IllegalStateException("Account with ID: " + accountId + " does not exist."));
    }

    // Get an account by customer_id
    public List<Account> getAccountByCustomer(Integer customerId) {
        return accountRepository.selectAccountsByCustomer(customerId);
    }

    // Creates a new account
    public Account createAccount(Account account){

        Customer customer = customerRepository.findById(account.getCustomer().getCustomer_id()).orElseThrow(() -> new IllegalStateException("Fail"));
        account.setCustomer(customer);
        return accountRepository.save(account);
    }

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
    public Account updateAccount(Long accountId, Account account) {
        Account updatedAccount = accountRepository.findById(accountId).orElseThrow(() ->
                new IllegalStateException("Customer with ID: " + accountId + " does not exist."));

        if (account.getCurrent_balance() > 0) {
            updatedAccount.setCurrent_balance(account.getCurrent_balance());
        }

        return accountRepository.save(updatedAccount);
    }
}
