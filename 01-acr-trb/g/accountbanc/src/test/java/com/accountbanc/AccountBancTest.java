package com.accountbanc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AccountBancTest {

  AccountBanc accountBanc = new AccountBanc();

  @Test
  void testDeposit() {
    double depositResponse = accountBanc.deposit(50.50);
    assertEquals(50.50, depositResponse);
  }

  @Test
  void testWithdraw() {
    accountBanc.deposit(50.50);
    double withdrawResponse = accountBanc.withdraw(5.50);
    assertEquals(45.00, withdrawResponse);
  }

}
