package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocSwaggerConfig {

    @Bean
    public OpenAPI swagger(){
        return new OpenAPI()
                .info(new Info()
                        .title("")
                        .version("v1")
                        .description("Some description API java 17 and Spring boot for validatd token")
                        .termsOfService("https://github.com/maicon-florencio/api-authorized")
                        .license(new License().name("Apache 2.0")
                                .url("https://github.com/maicon-florencio/api-authorized")));
    }
}
