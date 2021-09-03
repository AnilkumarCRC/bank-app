package crc.bank;

import crc.constants.AccountTypes;

public class Account
{
    private int accountNumber;
    private double balance;
    private AccountTypes type;

    public Account(int accountNumber, AccountTypes type,  double balance)
    {
        this.accountNumber = accountNumber;
        this.type = type;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void credit(double amount) {
        balance += amount;
    }

    public void debit(double amount) {
        balance -= amount;
    }

    public int getAccountNumber()
    {
        return accountNumber;
    }
}

