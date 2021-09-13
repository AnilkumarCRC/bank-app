import crc.bank.Banker;
import crc.bank.Transaction;
import crc.constants.AccountTypes;
import crc.exception.GlobalExceptionMessage;

import java.util.List;
import java.util.Scanner;

public class BankOperations {

    public  BankOperations() throws GlobalExceptionMessage{
        Banker bankActivities = new Banker();
        Scanner scan =  new Scanner(System.in);
        int operated, accountNumber, fromAccountNumber, toAccountNumber, amount;

        do {
            System.out.println("************************************************************************");
            System.out.println("\t\t\tWelcome to Banking System ");
            System.out.println("************************************************************************\n");
            System.out.println("Select your option from below list");
            System.out.println("1. Account registration");
            System.out.println("2. Transfer amount to the account");
            System.out.println("3. Deposit amount to the account");
            System.out.println("4. Withdraw amount to the account");
            System.out.println("5. Account statements");
            System.out.println("5. List all transactions");
            System.out.println("6. Exit\n");
            System.out.print("Enter your option: ");
            operated = scan.nextInt();
            switch(operated){
                case 1:
                    String name, mobile, address, type;

                    name = mobile = address = type = "";
                    System.out.println("For New Account Register updated details");
                    System.out.println("Enter your name: ");
                    name += scan.nextLine();
                    System.out.println("Enter your mobile number: ");
                    mobile += scan.nextLine();
                    System.out.println("Enter your address:");
                    address += scan.nextLine();
                    System.out.println("Select your account type as (Saving/Current):");
                    type += scan.nextLine();
                    if(type == "Current" || type == "current")
                        bankActivities.createAccount(name,address,mobile,AccountTypes.Current);
                    else
                        bankActivities.createAccount(name,address,mobile,AccountTypes.Saving);
                    break;
                case 2:
                    String confirm = "";
                    System.out.println("Enter your account number");
                    fromAccountNumber = scan.nextInt();
                    System.out.println("Enter beneficiary account number");
                    toAccountNumber = scan.nextInt();
                    System.out.println("Enter amount you want to transfer");
                    amount = scan.nextInt();
                    if(amount >= 5000) {
                        System.out.println("Would to like to transfer more than 5000, Confirm (Y/N) ");
                        confirm = scan.next();
                        if (confirm == "Y" || confirm == "y") {
                            bankActivities.transfer(fromAccountNumber,toAccountNumber,amount);
                        }
                    } else {
                        bankActivities.transfer(fromAccountNumber,toAccountNumber,amount);
                    }
                    break;
                case 3:
                    System.out.println("Enter your account number");
                    accountNumber = scan.nextInt();
                    System.out.println("Enter amount you want to deposit");
                    amount = scan.nextInt();
                    bankActivities.deposit(accountNumber,amount);
                    if(amount <= 0 || amount <= 0.0) {
                        throw new GlobalExceptionMessage("Amount should be more than 0");
                    }
                    break;
                case 4:
                    System.out.println("Enter your account number");
                    accountNumber = scan.nextInt();
                    System.out.println("Enter amount you want to withdraw");
                    amount = scan.nextInt();
                    if(amount <= 0 || amount <= 0.0) {
                        throw new GlobalExceptionMessage("Amount should be more than 0");
                    }
                    bankActivities.withdraw(accountNumber,amount);
                    break;
                case 5:
                    System.out.println("Enter your account number");
                    accountNumber = scan.nextInt();
                    List<String> transactions = bankActivities.findAccountTransactions(accountNumber);
                    if(transactions.isEmpty()){
                        throw new GlobalExceptionMessage("No Transactions found for given account "+accountNumber);
                    }
                    for (String transaction : transactions) {
                        System.out.println(transaction);
                    }
                    break;
                case 6:
                    System.out.println("List of transactions");
                    List<Transaction> transactionsList = bankActivities.listOfTransactions();
                    if(transactionsList.isEmpty()){
                        throw new GlobalExceptionMessage("No transaction details found");
                    }
                    for (Transaction transaction : transactionsList) {
                        System.out.println (transaction.accountTransaction());
                    }
                    break;
            }
        }while(operated < 7);
        System.out.println("Thanks for banking with us..!");
    }
}
