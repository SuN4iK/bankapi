package com.example.bankapi.model.entities;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public class Transaction {
  private UUID id;
  private UUID debitAccountId;
  private UUID creditAccountId;
  private BigDecimal amount;
  private String description;
  private OffsetDateTime createdAt;
}
