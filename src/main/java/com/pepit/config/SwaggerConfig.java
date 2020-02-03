package com.pepit.config;

import com.google.common.base.Predicates;
import com.google.common.net.HttpHeaders;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static freemarker.template.utility.Collections12.singletonList;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
    @Bean
    public Docket api() {
        //List<SecurityScheme> schemeList = new ArrayList<>();
        //schemeList.add(new ApiKey(HttpHeaders.AUTHORIZATION, "JWT", "header"));
        return new Docket(DocumentationType.SWAGGER_2)
                //.produces(Collections.singleton("application/json"))
                //.consumes(Collections.singleton("application/json"))
                .ignoredParameterTypes(Authentication.class)
                //.securitySchemes(schemeList)
                //.useDefaultResponseMessages(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.pepit.controllers"))
                .paths(PathSelectors.any())
                .build();
    } **/

    @Bean
    public Docket api() {
        return new Docket(SWAGGER_2)
                .securitySchemes(singletonList(new ApiKey("JWT", AUTHORIZATION,"header")))
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
                .paths(PathSelectors.any())
                .build();
    }


}