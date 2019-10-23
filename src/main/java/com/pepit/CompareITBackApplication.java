package com.pepit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class CompareITBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(CompareITBackApplication.class, args);
    }

}
