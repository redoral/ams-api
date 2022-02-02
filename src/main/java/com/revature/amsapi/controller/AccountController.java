package com.revature.amsapi.controller;

import com.revature.amsapi.entity.Account;
import com.revature.amsapi.exception.AccountNotFoundException;
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
    public AccountController(AccountService accountService){ this.accountService = accountService; }

    @GetMapping
    public List<Account> getAccounts(){ return accountService.getAccounts(); }

    @GetMapping(path = "{accountId}")
    public Account getAccountById(@PathVariable("accountId") Long accountId) throws AccountNotFoundException { return accountService.getAccount(accountId); }

    @GetMapping(path = "customer/{customerId}")
    public List<Account> getAccountsByCustomer(@PathVariable Integer customerId) throws AccountNotFoundException { return accountService.getAccountByCustomer(customerId); }

    @PostMapping
    public Account createAccount(@RequestBody Account account) throws AccountNotFoundException { return accountService.createAccount(account); }

    @DeleteMapping(path = "{accountId}")
    public boolean deleteAccount(@PathVariable("accountId") Long accountId) throws AccountNotFoundException { return accountService.deleteAccount(accountId); }

    @PutMapping(path = "{accountId}")
    public Account updateAccount(@PathVariable("accountId") Long accountId, Account account) throws AccountNotFoundException { return accountService.updateAccount(accountId, account); }

}
