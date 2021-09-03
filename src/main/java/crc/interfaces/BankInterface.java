package crc.interfaces;

import crc.bank.Account;
import crc.exception.GlobalExceptionMessage;

public interface BankInterface {
     void deposit(int accountNumber, double amount)  throws GlobalExceptionMessage;
     void withdraw(int accountNumber, double amount)  throws GlobalExceptionMessage;
     void transfer(int fromAccountNumber, int toAccountNumber, double amount) throws GlobalExceptionMessage;
}
