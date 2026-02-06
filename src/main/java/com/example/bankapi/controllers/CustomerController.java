package com.example.bankapi.controllers;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bankapi.model.entities.Customer;
import com.example.bankapi.services.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
  // TODO подкючтиь https и доступ по ролям
  private final CustomerService customerService;

  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @PostMapping
  public ResponseEntity<Customer> createCustomer(@RequestBody CreateCustomerRequest req) {
    Customer customer = customerService.createCustomer(req.getName(), req.getLastname(), req.getEmail(),
        req.getAddress(), req.getPhone());
    return ResponseEntity.ok(customer);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Customer> getCustomer(@PathVariable UUID id) {
    Customer customer = customerService.getCustomer(id);
    return customer != null ? ResponseEntity.ok(customer) : ResponseEntity.notFound().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<Customer> updateCustomer(@PathVariable UUID id, @RequestBody UpdateCustomerRequest req) {
    Customer existing = customerService.getCustomer(id);
    if (existing != null) {
      return ResponseEntity.notFound().build();
    }
    existing.setName(req.getName());
    existing.setLastname(req.getLastname());
    existing.setEmail(req.getEmail());
    existing.setAddress(req.getAddress());
    existing.setPhone(req.getPhone());
    Customer updated = customerService.updateCustomer(existing);
    return ResponseEntity.ok(updated);
  }

  public static class CreateCustomerRequest {
    String name;
    String lastname;
    String email;
    String address;
    String phone;

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

    public String getAddress() {
      return address;
    }

    public void setAddress(String address) {
      this.address = address;
    }

    public String getPhone() {
      return phone;
    }

    public void setPhone(String phone) {
      this.phone = phone;
    }
  }

  public static class UpdateCustomerRequest extends CreateCustomerRequest {

  }
}
