package com.example.bankapi.model.entities;

import java.time.OffsetDateTime;
import java.util.UUID;
import com.example.bankapi.model.enums.ManagerStatus;

public class Manager {
  private UUID id;
  private ManagerStatus status;
  private String name;
  private String lastName;
  private String email;
  private String password;
  private OffsetDateTime createdAt;
  private OffsetDateTime updatedAt;
}
