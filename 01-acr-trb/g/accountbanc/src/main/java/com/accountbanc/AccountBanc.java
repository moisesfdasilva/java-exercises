package com.accountbanc;

public class AccountBanc {

  private double balance = 0;

  public double deposit(double value) {
    this.balance += value;
    return this.balance;
  }

  public double withdraw(double value) {
    this.balance -= value;
    return this.balance;
  }

}
