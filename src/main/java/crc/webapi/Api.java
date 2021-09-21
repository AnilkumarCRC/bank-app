package crc.webapi;

import crc.bank.Banker;
import crc.exception.GlobalExceptionMessage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages={"crc.webapi", "crc.bank"})
public class Api {

    public static void main(String[] args) throws GlobalExceptionMessage {
        ApplicationContext context = SpringApplication.run(Api.class,args);
//        Banker bank = context.getBean(Banker.class);
    }

    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Api.class);
    }
}
