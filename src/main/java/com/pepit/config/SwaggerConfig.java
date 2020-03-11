package com.pepit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

import static java.util.Collections.singletonList;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket jsonApi() {
        return new Docket(SWAGGER_2)
                .apiInfo(apiInfo())
                .securitySchemes(singletonList(new ApiKey("JWT", AUTHORIZATION, "header")))
                .securityContexts(singletonList(
                        SecurityContext.builder()
                                .securityReferences(
                                        singletonList(SecurityReference.builder()
                                                .reference("JWT")
                                                .scopes(new AuthorizationScope[0])
                                                .build()
                                        )
                                )
                                .build())
                )
                .select()

                .apis(RequestHandlerSelectors.basePackage("com.pepit.controllers"))
                .paths(PathSelectors.regex("(?!/error).+"))
                .paths(PathSelectors.regex("(?!/alert).+"))
                .paths(PathSelectors.regex("(?!/filter).+"))
                .paths(PathSelectors.regex("(?!/heartbeat).+"))
                .paths(PathSelectors.regex("(?!/review).+"))
                .paths(PathSelectors.regex("(?!/model).+"))
                .paths(PathSelectors.regex("(?!/product).+"))
                .paths(PathSelectors.regex("(?!/modelproperty).+"))
                .paths(PathSelectors.regex("(?!/websiteconfig).+"))
                .paths(PathSelectors.regex("(?!/user).+"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "REST API",
                "Rest API for supplier",
                "API V1",
                "Terms of service",
                new Contact("Compare'IT Team", "http://demo.comparit.fr/", "comparite@gmail.com"),
                "License of API", "API license URL", Collections.emptyList());
    }
}