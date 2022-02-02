package com.revature.amsapi.service;

import com.revature.amsapi.entity.Account;
import com.revature.amsapi.entity.Customer;
import com.revature.amsapi.exception.AccountNotFoundException;
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

    @Autowired
    public AccountService(CustomerRepository customerRepository, AccountRepository accountRepository){
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

    public List<Account> getAccounts(){
        return accountRepository.findAll();
    }

    public Account getAccount(Long accountId) throws AccountNotFoundException {
        return accountRepository.findById(accountId).orElseThrow(() -> new AccountNotFoundException("Account with account number: " + accountId + " does not exist."));
    }

    public List<Account> getAccountByCustomer(Integer customerId) {
        return accountRepository.selectAccountsByCustomer(customerId);
    }

    public Account createAccount(Account account) throws AccountNotFoundException{
        Customer customer = customerRepository.findById(account.getCustomer().getCustomer_id()).orElseThrow(() -> new AccountNotFoundException("Account with account number: " + account.getAccount_number() + " does not exist."));
        account.setCustomer(customer);
        return accountRepository.save(account);
    }

    public boolean deleteAccount(Long accountId) throws AccountNotFoundException {
        accountRepository.findById(accountId).orElseThrow(() -> new AccountNotFoundException("Account with account number: " + accountId + " does not exist."));
        accountRepository.deleteById(accountId);
        return true;
    }

    @Transactional
    public Account updateAccount(Long accountId, Account account) throws AccountNotFoundException {
        accountRepository.findById(accountId).orElseThrow(() -> new AccountNotFoundException("Account with account number: " + accountId + " does not exist."));
        return accountRepository.save(account);
    }
}
