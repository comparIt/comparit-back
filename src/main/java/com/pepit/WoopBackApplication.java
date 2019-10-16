package com.pepit;

import com.pepit.bean.ProductDB;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class WoopBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(WoopBackApplication.class, args);
    }

    @Bean
    public ProductDB productDB(){
        return new ProductDB();
    }

}
