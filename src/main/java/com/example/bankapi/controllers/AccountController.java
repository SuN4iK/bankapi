package com.example.bankapi.controllers;

import com.example.bankapi.model.entities.Account;
import com.example.bankapi.model.entities.Customer;
import com.example.bankapi.model.enums.Currency;
import com.example.bankapi.services.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody CreateAccountRequest req) {
        Account account = accountService.createAccount(
                req.getCustomerId(), req.getName(), req.getCurrency(), req.getInitialBalance());
        return ResponseEntity.ok(account);
    }

    @PostMapping("/{id}/deposit")
    public ResponseEntity<Void> deposit(@PathVariable UUID id, @RequestBody AmountRequest req) {
        accountService.deposit(id, req.getAmount());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/withdraw")
    public ResponseEntity<Void> withdraw(@PathVariable UUID id, @RequestBody AmountRequest req) {
        accountService.withdraw(id, req.getAmount());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/transfer")
    public ResponseEntity<Void> transfer(@RequestBody TransferRequest req) {
        accountService.transfer(req.getFromAccountId(), req.getToAccountId(), req.getAmount());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable UUID id) {
        Account account = accountService.getAccount(id);
        return account != null ? ResponseEntity.ok(account) : ResponseEntity.notFound().build();
    }

    public static class CreateAccountRequest {
        private UUID customerId;
        private String name;
        private Currency currency;
        private BigDecimal initialBalance;
        public UUID getCustomerId() { return customerId; }
        public void setCustomerId(UUID customerId) { this.customerId = customerId; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public Currency getCurrency() { return currency; }
        public void setCurrency(Currency currency) { this.currency = currency; }
        public BigDecimal getInitialBalance() { return initialBalance; }
        public void setInitialBalance(BigDecimal initialBalance) { this.initialBalance = initialBalance; }
    }

    public static class AmountRequest {
        private BigDecimal amount;
        public BigDecimal getAmount() { return amount; }
        public void setAmount(BigDecimal amount) { this.amount = amount; }
    }

    public static class TransferRequest {
        private UUID fromAccountId;
        private UUID toAccountId;
        private BigDecimal amount;
        public UUID getFromAccountId() { return fromAccountId; }
        public void setFromAccountId(UUID fromAccountId) { this.fromAccountId = fromAccountId; }
        public UUID getToAccountId() { return toAccountId; }
        public void setToAccountId(UUID toAccountId) { this.toAccountId = toAccountId; }
        public BigDecimal getAmount() { return amount; }
        public void setAmount(BigDecimal amount) { this.amount =  amount; }
    }

}
