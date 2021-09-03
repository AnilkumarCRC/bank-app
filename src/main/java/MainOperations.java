import crc.constants.AccountTypes;
import crc.bank.Banker;
import crc.exception.GlobalExceptionMessage;

public class MainOperations {

    public static void main(String[] args) throws GlobalExceptionMessage {
        Banker c= new Banker();


        int accountNumber = c.createAccount("Anil","India","9899898989", AccountTypes.Saving);
        int accountNumber2 = c.createAccount("Anil","India","9899898989", AccountTypes.Saving);

        System.out.println("Account created successfully with account number "+accountNumber);

        c.deposit(accountNumber, 100);
        System.out.println(c.getBalance(accountNumber));
        c.withdraw(accountNumber,50);
        System.out.println(c.getBalance(accountNumber));

        c.transfer(accountNumber,accountNumber2,10);
        System.out.println(c.getBalance(accountNumber));
        System.out.println(c.getBalance(accountNumber2));


    }
}
