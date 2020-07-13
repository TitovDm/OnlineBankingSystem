package com.example.banking.repository;

import com.example.banking.models.SavingsAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavingsAccountRepository extends JpaRepository<SavingsAccount,Long> {
        SavingsAccount findByAccountNumber (int accountNumber);
}