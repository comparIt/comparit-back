package com.pepit;

import com.pepit.repository.ProductRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class WoopBackApplication {

    public static void main(String[] args) {
        new ProductRepository().testRequest();

        SpringApplication.run(WoopBackApplication.class, args);

    }

}
