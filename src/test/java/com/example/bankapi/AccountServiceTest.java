package com.example.bankapi;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.bankapi.model.entities.Account;
import com.example.bankapi.model.enums.Currency;
import com.example.bankapi.services.AccountService;
import com.example.bankapi.services.TransactionService;

@SpringBootTest // TODO написать про точки входа
public class AccountServiceTest {

  private AccountService accountService;
  private TransactionService transactionService;
  private UUID customerId;

  @BeforeEach
  void setUp() {
    transactionService = new TransactionService();
    accountService = new AccountService(transactionService);
    customerId = UUID.randomUUID();
  }

  @Test
  void testCreateAccount() {
    Account account = accountService.createAccount(customerId, "testAccount", Currency.USD, BigDecimal.valueOf(100));
    Assertions.assertNotNull(account.getId(), "Идентификатора нет");
    Assertions.assertEquals("testAccount", account.getName());
    Assertions.assertEquals(BigDecimal.valueOf(100), account.getBalance());
    Assertions.assertEquals(Currency.USD, account.getCurrency());
  }

  @Test
  void testDeposit() {
    Account account = accountService.createAccount(customerId, "DepositAccount", Currency.USD, BigDecimal.valueOf(100));
    accountService.deposit(account.getId(), BigDecimal.valueOf(50));
    Assertions.assertEquals(BigDecimal.valueOf(150), accountService.getAccount(account.getId()).getBalance());
  }

  @Test
  void testWithdraw() {
    Account account = accountService.createAccount(customerId, "Withdraw test", Currency.USD, BigDecimal.valueOf(100));
    accountService.withdraw(account.getId(), BigDecimal.valueOf(40));
    Assertions.assertEquals(BigDecimal.valueOf(60), accountService.getAccount(account.getId()).getBalance());
  }

  @Test
  void testWithdrawOverflow() {
    Account account = accountService.createAccount(customerId, "Withdraw test", Currency.USD, BigDecimal.valueOf(100));
    RuntimeException ex = Assertions.assertThrows(RuntimeException.class,
        () -> accountService.withdraw(account.getId(), BigDecimal.valueOf(1000)));
    Assertions.assertEquals("Недостаточно средств", ex.getMessage());
  }

  @Test
  void testTransfer() {
    Account accountFrom = accountService.createAccount(customerId, "Transfer from", Currency.USD,
        BigDecimal.valueOf(200));
    Account accountTo = accountService.createAccount(UUID.randomUUID(), "Transfer to", Currency.USD,
        BigDecimal.valueOf(100));
    accountService.transfer(accountFrom.getId(), accountTo.getId(), BigDecimal.valueOf(50));
    Assertions.assertEquals(BigDecimal.valueOf(150), accountService.getAccount(accountFrom.getId()).getBalance());
    Assertions.assertEquals(BigDecimal.valueOf(150), accountService.getAccount(accountTo.getId()).getBalance());

  }
}
