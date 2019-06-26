package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@ComponentScan(value = "com.zkpt, com.common")
public class ZkptApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZkptApplication.class, args);
    }
}
