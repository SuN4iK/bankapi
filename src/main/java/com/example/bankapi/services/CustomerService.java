package com.example.bankapi.services;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.example.bankapi.model.entities.Customer;
import com.example.bankapi.model.enums.CustomerStatus;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
  private Map<UUID, Customer> customerRepository = new HashMap<>();

  public Customer createCustomer(String name, String lastname, String email, String address, String phone) { // TODO
                                                                                                             // сделать
                                                                                                             // валидацию

    Customer customer = new Customer();
    customer.setId(UUID.randomUUID());
    customer.setName(name);
    customer.setLastname(lastname);
    customer.setEmail(email);
    customer.setAddress(address);
    customer.setPhone(phone);
    customer.setCreatedAt(OffsetDateTime.now());
    customer.setUpdatedAt(OffsetDateTime.now());
    customer.setStatus(CustomerStatus.ACTIVE);
    customerRepository.put(customer.getId(), customer);
    return customer;
  }

  public Customer updateCustomer(Customer customer) {
    customer.setUpdatedAt(OffsetDateTime.now());
    customerRepository.put(customer.getId(), customer);
    return customer;
  }

  public Customer getCustomer(UUID customerId) {
    return customerRepository.get(customerId);
  }

  public Map<UUID, Customer> getAll() {
    return customerRepository;
  }
}
