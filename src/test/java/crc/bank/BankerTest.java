package crc.bank;

import crc.constants.AccountTypes;
import crc.exception.GlobalExceptionMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BankerTest {

    Banker account = new Banker();
    int accountNumber;

    @BeforeEach
    void setUp() {
        accountNumber = account.createAccount("Anil", "India", "999999999", AccountTypes.Saving);
    }

    @Test
    void transfer() throws GlobalExceptionMessage{
        account.deposit(accountNumber,4000);
        int accountNumber2 = account.createAccount("AnilKumar", "India", "999999999", AccountTypes.Current);
        account.transfer(accountNumber,accountNumber2,1000);
        assertEquals(1000.0,account.getBalance(accountNumber2));
    }

    @Test
    void transferAccountFailed() throws GlobalExceptionMessage{
        account.deposit(accountNumber,1000);
        Exception accountException = assertThrows(GlobalExceptionMessage.class, () -> {
            account.transfer(accountNumber,12345,1000);
        });
        assertEquals("Account not found",accountException.getMessage());
    }

    @Test
    void transferAmountFailed() throws GlobalExceptionMessage{
        int accountNumber2 = account.createAccount("AnilKumar", "India", "999999999", AccountTypes.Current);
        Exception amountException = assertThrows(GlobalExceptionMessage.class, () -> {
            account.transfer(accountNumber,accountNumber2,1000);
        });
        assertEquals("Account does not have sufficient amount to do transaction",amountException.getMessage());
    }

    @Test
    void findAccountTransactions()  throws GlobalExceptionMessage {
        account.deposit(accountNumber,1000);
        account.deposit(accountNumber,1000);
        int list= account.findAccountTransactions(accountNumber).size();
        assertTrue(list > 0);
    }

    @Test
    void findAccountTransactionsFailed()  throws GlobalExceptionMessage {
        Exception transactionException = assertThrows(GlobalExceptionMessage.class, () -> {
            account.findAccountTransactions(accountNumber);
        });
        assertEquals("No transactions found",transactionException.getMessage());
    }
}