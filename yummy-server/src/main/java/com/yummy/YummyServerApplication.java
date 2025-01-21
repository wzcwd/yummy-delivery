package com.yummy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class YummyServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(YummyServerApplication.class, args);
    }

}
