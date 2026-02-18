package com.example.bankapi.repository;

import com.example.bankapi.model.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
	List<Transaction> findByDebitOrCreditAccountId(UUID debitID, UUID creditId);
}
