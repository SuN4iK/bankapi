package com.example.bankapi.services;

import java.util.List;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.example.bankapi.model.entities.Transaction;

public class TransactionService {

  private Map<UUID, Transaction> transactionRepository = new HashMap<>();

  public void createTransaction(UUID debitAccount, UUID creditAccount, BigDecimal amount, String description) {
    Transaction transaction = new Transaction();
    transaction.setId(UUID.randomUUID());
    transaction.setDebitAccountId(debitAccount);
    transaction.setCreditAccountId(creditAccount);
    transaction.setAmount(amount);
    transaction.setDescription(description);
    transaction.setCreatedAt(OffsetDateTime.now());
    transactionRepository.put(transaction.getId(), transaction);

  }

  public List<Transaction> getTransactionsForAccount(UUID accountId) {
    List<Transaction> transactions = new ArrayList<>();
    for (Transaction transaction : transactionRepository.values()) {
      if ((transaction.getDebitAccountId() != null && transaction.getDebitAccountId().equals(accountId))
          || (transaction.getCreditAccountId() != null && transaction.getCreditAccountId().equals(accountId))) {
        transactions.add(transaction);
      }
    }
    return transactions;

  }
}
