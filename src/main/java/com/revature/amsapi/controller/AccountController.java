package com.revature.amsapi.controller;

import com.revature.amsapi.entity.Account;
import com.revature.amsapi.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/accounts")
@CrossOrigin
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @GetMapping
    public List<Account> getAccounts(){
        return accountService.getAccounts();
    }

    @GetMapping(path = "{accountId}")
    public Account getAccountById(@PathVariable("accountId") Long accountId) {
        try {
            return accountService.getAccount(accountId);
        } catch (IllegalStateException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @GetMapping(path = "customer/{customerId}")
    public List<Account> getAccountsByCustomer(@PathVariable Integer customerId) {
        return accountService.getAccountByCustomer(customerId);
    }

    @PostMapping
    public Account createAccount(@RequestBody Account account){ return accountService.createAccount(account); }

    @DeleteMapping(path = "{accountId}")
    public boolean deleteAccount(@PathVariable("accountId") Long accountId){
        try {
            accountService.deleteAccount(accountId);
            return true;
        } catch(IllegalStateException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @PutMapping(path = "{accountId}")
    public Account updateAccount(@PathVariable("accountId") Long accountId, Account account){
        try {
            Account updatedAccount = accountService.updateAccount(accountId, account);
            return updatedAccount;
        } catch (IllegalStateException e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
