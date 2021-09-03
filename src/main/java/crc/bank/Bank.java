package crc.bank;

import crc.constants.AccountTypes;
import crc.constants.TransactionTypes;
import crc.exception.GlobalExceptionMessage;
import crc.interfaces.BankInterface;

import java.util.ArrayList;
import java.util.List;

public abstract class Bank implements BankInterface {

    List<Account> accounts = new ArrayList<>();
    List<Customer> customers = new ArrayList<>();
    List<Transaction> transactions = new ArrayList<>();

    protected int createBankAccount(String name, String address, String mobile, AccountTypes accountType) {
        int uniqueId = generateUid();
        this.addCustomer(new Customer(name,address, mobile,uniqueId));
        Account newAccount = new Account(uniqueId, accountType,0);
        accounts.add(newAccount);
        return newAccount.getAccountNumber();
    }

    private static int generateUid() {
        return (int)(Math.random()*(11111-9+1)+9);
    }

    private Account getAccount(int accountNumber) {
        Account accountInfo = accounts.stream().filter(account -> account.getAccountNumber() == accountNumber ).findAny().orElse(null);
        return  accountInfo;
    }

    protected void credit(int accountNumber, double amount) throws GlobalExceptionMessage {
        getAccount(accountNumber).credit(amount);
        transactions.add(new Transaction(accountNumber, TransactionTypes.credit,""));
    }

    protected void debit(int accountNumber, double amount) {
        getAccount(accountNumber).debit(amount);
        transactions.add(new Transaction(accountNumber, TransactionTypes.debit,""));
    }

    protected double getBalance(int accountNumber) {
        return getAccount(accountNumber).getBalance();
    }

    private void addCustomer(Customer customerDetails){
        customers.add(customerDetails);
    }

    protected void transferAmount(int fromAccount, int toAccount, double amount)  throws GlobalExceptionMessage{
        this.debit(fromAccount, amount);
        this.credit(toAccount, amount);
    }
}
