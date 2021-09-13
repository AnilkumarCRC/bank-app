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
    void creditAccountAndAmountFalse()  throws GlobalExceptionMessage{
        Exception creditAmountException = assertThrows(GlobalExceptionMessage.class, () -> {
            account.deposit(accountNumber,-10);
        });
        Exception creditAccountException = assertThrows(GlobalExceptionMessage.class, () -> {
            account.deposit(123456,1000);
        });
        Exception creditAccountAndAmountException = assertThrows(GlobalExceptionMessage.class, () -> {
            account.deposit(123456,-100);
        });
        assertEquals("Minimum amount should be more than 1 rupee",creditAmountException.getMessage());
        assertEquals("Account not found",creditAccountException.getMessage());
        assertEquals("Minimum amount should be more than 1 rupee",creditAccountAndAmountException.getMessage());
    }

    @Test
    void debit()  throws GlobalExceptionMessage{
        account.deposit(accountNumber,100);
        account.withdraw(accountNumber,50);
        assertEquals(50,account.getBalance(accountNumber));
    }

    @Test
    void debitAccountAndAmountFalse()  throws GlobalExceptionMessage{
        account.deposit(accountNumber,100);
        Exception debitAmountException = assertThrows(GlobalExceptionMessage.class, () -> {
            account.withdraw(accountNumber,-10);
        });
        Exception debitAccountException = assertThrows(GlobalExceptionMessage.class, () -> {
            account.withdraw(123456,100);
        });
        Exception debitAccountAndAmountException = assertThrows(GlobalExceptionMessage.class, () -> {
            account.withdraw(123456,-100);
        });
        Exception debitException = assertThrows(GlobalExceptionMessage.class, () -> {
            account.withdraw(accountNumber,1000);
        });
        assertAll(() -> {
            assertEquals("Account does not have sufficient amount to do transaction",debitException.getMessage());
            assertEquals("Minimum amount should be more than 1 rupee",debitAmountException.getMessage());
            assertEquals("Account not found",debitAccountException.getMessage());
            assertEquals("Minimum amount should be more than 1 rupee",debitAccountAndAmountException.getMessage());
        });
    }

    @Test
    void getAccountNumber()  throws GlobalExceptionMessage{
        assertEquals(accountNumber,account.searchAccountDetails(accountNumber).getAccountNumber());
    }

    @Test
    void getBalanceFailed() throws GlobalExceptionMessage{
        assertNotEquals(100.0,account.getBalance(accountNumber));
    }

    @Test
    void getAccountNumberFailed()  throws GlobalExceptionMessage{
        Exception exception = assertThrows(GlobalExceptionMessage.class, () -> {
            account.searchAccountDetails(123456).getAccountNumber();
        });
        assertEquals("Account not found",exception.getMessage());
    }
}