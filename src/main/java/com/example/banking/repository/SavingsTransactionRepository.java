package com.example.banking.repository;

import com.example.banking.models.SavingsTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavingsTransactionRepository  extends JpaRepository<SavingsTransaction,Long> {

}