package com.example.bankapi.model.entities;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;
import com.example.bankapi.model.enums.Currency;
import com.example.bankapi.model.enums.AccountStatus;

public class Account {

  private UUID id;
  private UUID customerId;
  private String name;
  private BigDecimal balance;
  private Currency currency;
  private OffsetDateTime createdAt;
  private OffsetDateTime updatedAt;
  private AccountStatus status;

  public void setId(UUID id) {
    this.id = id;
  }

  public void setCustomerId(UUID customerId) {
    this.customerId = customerId;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

  public void setCurrency(Currency currency) {
    this.currency = currency;
  }

  public void setCreatedAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public void setUpdatedAt(OffsetDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  public void setStatus(AccountStatus status) {
    this.status = status;
  }

  public UUID getId() {
    return id;
  }

  public UUID getCustomerId() {
    return customerId;
  }

  public String getName() {
    return name;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public Currency getCurrency() {
    return currency;
  }

  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public OffsetDateTime getUpdatedAt() {
    return updatedAt;
  }

  public AccountStatus getStatus() {
    return status;
  }
}
