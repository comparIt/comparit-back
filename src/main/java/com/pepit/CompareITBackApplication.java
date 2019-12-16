package com.pepit;

import com.pepit.config.FileStorageProperties;
import com.pepit.bean.ProductDB;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
@EnableAsync
public class CompareITBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(CompareITBackApplication.class, args);
    }

    @Bean
    public ProductDB productDB(){
        return new ProductDB();
    }

}
