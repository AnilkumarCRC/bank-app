package crc.webapi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ser.std.BooleanSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import crc.bank.Banker;
import crc.constants.AccountTypes;
import crc.exception.GlobalExceptionMessage;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RestController
@Component
@ComponentScan(basePackages={"crc.webapi", "crc.bank"})
@RequestMapping("/api/v1")
public class ApiUrls {
//    @Autowired(required=true)
    Banker bank = new Banker();

    @PostMapping(value = "/newAccount", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String createAccount(@RequestBody JsonNode jsonInput) {
        String name = jsonInput.get("name").asText();
        String address = jsonInput.get("address").asText();
        String mobile = jsonInput.get("mobile").asText();
        String type = jsonInput.get("type").asText();
        int created;
        if(type.equalsIgnoreCase("current")) {
            created = bank.createAccount(name, address, mobile, AccountTypes.Current);
        } else {
            created = bank.createAccount(name, address, mobile, AccountTypes.Saving);
        }

        if(created > 0)
            return "Account created with your bank with account number "+created;
        else
            return "Account not crated, try again after sometime";
    }

    @GetMapping(value = "/checkBalance", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String getAccountBalance(@RequestBody JsonNode jsonInput)   throws GlobalExceptionMessage {
        try {
            int account = jsonInput.get("account").asInt();
            return "Your account: "+account+"  balance is  "+bank.getBalance(account);
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @PostMapping(value = "/deposit", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String depositAmount(@RequestBody JsonNode jsonInput)   throws GlobalExceptionMessage {
        try {
            int account = jsonInput.get("account").asInt();
            int amount = jsonInput.get("amount").asInt();
            bank.deposit(account,amount);
            return "Your account: "+account+" is credited with "+amount;
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @PostMapping(value = "/withdraw", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String withdrawAmount(@RequestBody JsonNode jsonInput)   throws GlobalExceptionMessage {
        try {
            int account = jsonInput.get("account").asInt();
            int amount = jsonInput.get("amount").asInt();
            bank.withdraw(account,amount);
            return "Your account: "+account+" is debited with "+amount;
        }catch (Exception e){
            return e.getMessage();
        }
    }

//    @RequestMapping(method = RequestMethod.POST,value = "/transfer", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/transfer", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String transferAmount(@RequestBody JsonNode jsonInput)   throws GlobalExceptionMessage {
        try {
            int fromAccount = jsonInput.get("fromAccount").asInt();
            int toAccount = jsonInput.get("toAccount").asInt();
            int amount = jsonInput.get("amount").asInt();
            bank.transfer(fromAccount,toAccount,amount);
            return "Amount is transferred successfully";
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @GetMapping(value = "statements", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String  accountStatements(@RequestBody JsonNode jsonInput) {
        try {
            int account = jsonInput.get("account").asInt();
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            String pojoJson = gson.toJson( (bank.findAccountTransactions(account)));
           return pojoJson;
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}