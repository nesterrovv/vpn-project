package com.nesterrovv.vpn.subscription.controller;

import com.nesterrovv.vpn.subscription.entity.Account;
import com.nesterrovv.vpn.subscription.serivce.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/subscription/accounts")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<Account> createAccount (
        @RequestParam String username,
        @RequestParam boolean isMainAccount) {
        Account newAccount = accountService.createAccount(username, isMainAccount, Collections.emptySet());
        return new ResponseEntity<>(newAccount, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
        Account account = accountService.findById(id);
        if (account != null) {
            return new ResponseEntity<>(account, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}/add-linked-account")
    public ResponseEntity<String> addLinkedAccount(@PathVariable Long id, @RequestBody Account linkedAccount) {
        String result = accountService.addLinkedAccount(id, linkedAccount);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/{id}/remove-linked-account")
    public ResponseEntity<String> removeLinkedAccount(@PathVariable Long id, @RequestBody Account linkedAccount) {
        String result = accountService.removeLinkedAccount(id, linkedAccount);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        accountService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}