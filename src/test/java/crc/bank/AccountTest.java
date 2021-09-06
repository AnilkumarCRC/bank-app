package crc.bank;


import crc.constants.AccountTypes;
import crc.exception.GlobalExceptionMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    Banker account = new Banker();
    int accountNumber;

    @BeforeEach
    void setUp() {
        accountNumber = account.createAccount("Anil","India","999999999", AccountTypes.Saving);
    }

    @Test
    void getBalance() throws GlobalExceptionMessage{
        assertEquals(0.0,account.getBalance(accountNumber));
    }

    @Test
    void credit()  throws GlobalExceptionMessage {
        account.deposit(accountNumber,100);
        assertEquals(100,account.getBalance(accountNumber));
    }

    @Test
    void debit()  throws GlobalExceptionMessage{
        account.deposit(accountNumber,100);
        account.withdraw(accountNumber,50);
        assertEquals(50,account.getBalance(accountNumber));
    }

    @Test
    void getAccountNumber() {
        assertEquals(accountNumber,account.searchAccountDetails(accountNumber).getAccountNumber());
    }
}