package com.pepit;

import com.pepit.config.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
@EnableSwagger2
@EnableAsync
@EnableScheduling
@EnableTransactionManagement
public class CompareITBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(CompareITBackApplication.class, args);
    }

}
