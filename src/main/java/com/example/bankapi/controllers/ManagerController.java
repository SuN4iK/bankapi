package com.example.bankapi.controllers;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bankapi.model.entities.Manager;
import com.example.bankapi.services.ManagerService;

@RestController
@RequestMapping("/managers")
public class ManagerController {
  private final ManagerService managerService;

  public ManagerController(ManagerService managerService) {
    this.managerService = managerService;
  }

  @PostMapping
  public ResponseEntity<Manager> createManager(@RequestBody CreateManagerRequest req) {
    Manager manager = managerService.createManager(req.getName(), req.getLastname(), req.getEmail(), req.getPassword());
    return ResponseEntity.ok(manager);
  }

  @PostMapping("/{id}")
  public ResponseEntity<Manager> getManager(@PathVariable UUID id) {
    Manager manager = managerService.getManager(id);
    return manager != null ? ResponseEntity.ok(manager) : ResponseEntity.notFound().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<Manager> updateManager(@PathVariable UUID id, @RequestBody UpdateManagerRequest req) {
    Manager existing = managerService.getManager(id);
    if (existing != null) {
      return ResponseEntity.notFound().build();
    }
    existing.setName(req.getName());
    existing.setLastName(req.getLastname());
    existing.setEmail(req.getEmail());
    existing.setPassword(req.getPassword());
    Manager updated = managerService.updateManager(existing);
    return ResponseEntity.ok(updated);
  }

  public static class CreateManagerRequest {
    private String name;
    private String lastname;
    private String email;
    private String password;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getLastname() {
      return lastname;
    }

    public void setLastname(String lastname) {
      this.lastname = lastname;
    }

    public String getEmail() {
      return email;
    }

    public void setEmail(String email) {
      this.email = email;
    }

    public String getPassword() {
      return password;
    }

    public void setPassword(String password) {
      this.password = password;
    }
  }

  public static class UpdateManagerRequest extends CreateManagerRequest {

  }

}
