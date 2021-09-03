package crc.bank;

import crc.constants.AccountTypes;
import crc.exception.GlobalExceptionMessage;

public class Banker extends Bank{

    public int createAccount(String name, String address, String mobile, AccountTypes accountType) {
       return createBankAccount(name, address, mobile, accountType);
    }

    public double getBalance(int accountNumber) {
        return super.getBalance(accountNumber);
    }

    @Override
    public void deposit(int accountNumber, double amount)  throws GlobalExceptionMessage {
        this.credit(accountNumber,amount);
    }

    @Override
    public void withdraw(int accountNumber, double amount) {
        this.debit(accountNumber,amount);
    }

    @Override
    public void transfer(int fromAccount, int toAccount, double amount)  throws GlobalExceptionMessage{
        this.transferAmount(fromAccount, toAccount, amount);
    }

    protected Account searchAccountDetails(int accountNumber){
        Account accountInfo = accounts.stream().filter(account -> account.getAccountNumber() == accountNumber ).findAny().orElse(null);
        return  accountInfo;
    }

    private Transaction findTransactionDetails(int accountNumber){
        Transaction transactionsInfo = transactions.stream().filter(transaction -> transaction.getTransactionAccount() == accountNumber ).findAny().orElse(null);
        return transactionsInfo;
    }

    protected void listOfTransactions(int accountNumber){
        Transaction transactions = this.findTransactionDetails(accountNumber);
        for (Transaction transaction: Transactions
             ) {

        }
    }
}
