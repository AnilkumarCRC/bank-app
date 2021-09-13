package crc.bank;

import crc.constants.TransactionTypes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private int transactionId;
    private int transactionAccount;
    private String transactionMessage;
    private String transactionDate;
    private TransactionTypes transactionType;

    // should not be public
    public Transaction(int  accountNumber,TransactionTypes transactionType,String transactionMessage){
        LocalDateTime myDateObj = LocalDateTime.now();
        this.transactionId = generateTid();
        this.transactionAccount = accountNumber;
        this.transactionDate = myDateObj.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        this.transactionType = transactionType;
        this.transactionMessage = transactionMessage;
    }

    public String accountTransaction() {
        return "Transaction id-"+this.transactionId
                +" on the account "+this.transactionAccount
                +" is "+this.transactionType
                +" with "+this.transactionMessage
                +" On date "+this.transactionDate;
    }

    private static int generateTid() {
        return (int)(Math.random()*(22222-8+2)+8);
    }

    public int getAccount() {
        return transactionAccount;
    }
}
