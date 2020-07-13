package com.example.banking.repository;

import com.example.banking.models.PrimaryTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrimaryTransactionRepository extends JpaRepository<PrimaryTransaction,Long> {
}
