package crc.bank;

import crc.constants.AccountTypes;
import crc.exception.GlobalExceptionMessage;

import java.util.ArrayList;
import java.util.List;

public class Banker extends Bank {

    public int createAccount(String name, String address, String mobile, AccountTypes accountType) {
        return createBankAccount(name, address, mobile, accountType);
    }

    public double getBalance(int accountNumber)  throws GlobalExceptionMessage {
        return super.getBalance(accountNumber);
    }

    @Override
    public void deposit(int accountNumber, double amount)  throws GlobalExceptionMessage {
        this.credit(accountNumber,amount);
    }

    @Override
    public void withdraw(int accountNumber, double amount)  throws GlobalExceptionMessage{
        this.debit(accountNumber,amount);
    }

    @Override
    public void transfer(int fromAccount, int toAccount, double amount) throws GlobalExceptionMessage{
        this.transferAmount(fromAccount, toAccount, amount);
    }

    protected Account searchAccountDetails(int accountNumber)  throws GlobalExceptionMessage{
        return this.getAccount(accountNumber);
    }

    public List<String> findAccountTransactions(int accountNumber) throws GlobalExceptionMessage{
        List<Transaction> transactions = this.listOfTransactions();
        if(transactions.isEmpty()){
            throw new GlobalExceptionMessage("No transactions found");
        }
        List<String> accountTransactions = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getAccount() == accountNumber) {
                accountTransactions.add(transaction.accountTransaction());
            }
        }
        return accountTransactions;
    }
}