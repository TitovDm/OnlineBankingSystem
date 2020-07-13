package com.example.banking.services;

import com.example.banking.models.PrimaryAccount;
import com.example.banking.models.SavingsAccount;

import java.security.Principal;

public interface AccountService {

    PrimaryAccount createPrimaryAccount();
    SavingsAccount createSavingsAccount();
    void deposit(String accountType, double amount, Principal principal);
    void withdraw(String accountType, double amount, Principal principal);
    void withdrawFromPrimary (String accountType, double amount, Principal principal);
    void withdrawFromSavings (String accountType, double amount, Principal principal);


}