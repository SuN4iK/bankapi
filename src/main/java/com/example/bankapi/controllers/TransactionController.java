package com.example.bankapi.controllers;

import com.example.bankapi.model.entities.Transaction;
import com.example.bankapi.services.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<Transaction>> getTransactionForAccount(@PathVariable UUID accountId) {
        List<Transaction> txs = transactionService.getTransactionsForAccount(accountId);
        return ResponseEntity.ok(txs);
    }

}
