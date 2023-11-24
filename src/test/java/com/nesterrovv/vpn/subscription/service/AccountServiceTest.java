//package com.nesterrovv.vpn.subscription.service;
//
//import com.nesterrovv.vpn.subscription.entity.Account;
//import com.nesterrovv.vpn.subscription.repository.AccountRepository;
//import com.nesterrovv.vpn.subscription.serivce.AccountService;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.*;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//class AccountServiceTest {
//
//    @Mock
//    private AccountRepository accountRepository;
//
//    @InjectMocks
//    private AccountService accountService;
//
//    @Test
//    void testCreateAccount() {
//        when(accountRepository.save(any(Account.class))).thenReturn(new Account());
//        Account createdAccount = accountService.createAccount("testUser", true, new HashSet<>());
//        verify(accountRepository).save(any(Account.class));
//        assertNotNull(createdAccount);
//    }
//
//    @Test
//    void testFindById() {
//        when(accountRepository.findById(1L)).thenReturn(Optional.of(new Account()));
//        Account foundAccount = accountService.findById(1L);
//        verify(accountRepository).findById(1L);
//        assertNotNull(foundAccount);
//    }
//
//    @Test
//    void testAddLinkedAccount() {
//        when(accountRepository.findById(1L)).thenReturn(Optional.of(new Account()));
//        when(accountRepository.save(any(Account.class))).thenReturn(new Account());
//        Account linkedAccount = new Account();
//        linkedAccount.setUsername("linkedUser");
//        String result = accountService.addLinkedAccount(1L, linkedAccount);
//        verify(accountRepository, Mockito.times(1)).findById(1L); // Change from times(2) to times(1)
//        verify(accountRepository, Mockito.times(2)).save(any(Account.class));
//        assertEquals("Account added to set of linked accounts.", result);
//        Set<Account> linkedAccounts = new HashSet<>();
//        linkedAccounts.add(linkedAccount);
//        assertEquals(linkedAccounts, accountService.findById(1L).getLinkedAccounts());
//    }
//
//    @Test
//    void testRemoveLinkedAccount() {
//        when(accountRepository.findById(1L)).thenReturn(Optional.of(new Account()));
//        when(accountRepository.save(any(Account.class))).thenReturn(new Account());
//        Account linkedAccount = new Account();
//        linkedAccount.setUsername("linkedUser");
//        String result = accountService.addLinkedAccount(1L, linkedAccount);
//        String result2 = accountService.removeLinkedAccount(1L, linkedAccount);
//        verify(accountRepository, Mockito.times(2)).findById(1L); // Change from times(2) to times(1)
//        verify(accountRepository, Mockito.times(4)).save(any(Account.class));
//        assertEquals("Account added to set of linked accounts.", result);
//        Set<Account> linkedAccounts = new HashSet<>();
//        linkedAccounts.add(linkedAccount);
//        assertEquals(Collections.emptySet(), accountService.findById(1L).getLinkedAccounts());
//    }
//
//    @Test
//    void testDelete() {
//        accountService.delete(1L);
//        verify(accountRepository).deleteById(1L);
//    }
//
//    @Test
//    void testSave() {
//        when(accountRepository.save(any(Account.class))).thenReturn(new Account());
//        Account account = new Account();
//        Account savedAccount = accountService.save(account);
//        verify(accountRepository).save(any(Account.class));
//        assertNotNull(savedAccount);
//    }
//
//}
