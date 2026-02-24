package com.example.bankapi.services;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.UUID;

import com.example.bankapi.model.entities.Manager;
import com.example.bankapi.model.enums.ManagerStatus;
import org.springframework.stereotype.Service;

import com.example.bankapi.repository.ManagerRepository;

@Service
public class ManagerService {

  private ManagerRepository managerRepository;

  public ManagerService(ManagerRepository managerRepository) {
    this.managerRepository = managerRepository;
  }


  public Manager createManager(String name, String lastName, String email, String password) {
    Manager manager = new Manager();
    manager.setId(UUID.randomUUID());
    manager.setName(name);
    manager.setLastName(lastName);
    manager.setEmail(email);
    manager.setPassword(password);
    manager.setCreatedAt(OffsetDateTime.now());
    manager.setUpdatedAt(OffsetDateTime.now());
    manager.setStatus(ManagerStatus.ACTIVE);
    managerRepository.save(manager);
    return manager;
  }

  public Manager updateManager(Manager manager) {
    manager.setUpdatedAt(OffsetDateTime.now());
    managerRepository.save(manager);
    return manager;
  }

  public Manager getManager(UUID managerId) {
    return managerRepository.findById(managerId).orElse(null);
  }

  public List<Manager> getAll() {
    return managerRepository.findAll();
  }

}
