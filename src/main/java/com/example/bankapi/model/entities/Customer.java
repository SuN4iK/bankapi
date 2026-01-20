package com.example.bankapi.model.entities;

import java.time.OffsetDateTime;
import java.util.UUID;
import com.example.bankapi.model.enums.CustomerStatus;

public class Customer {
  private UUID id;
  private CustomerStatus status;
  private String name;
  private String lastname;
  private String email;
  private String address;
  private String phone;
  private OffsetDateTime createdAt;
  private OffsetDateTime updatedAt;
}
