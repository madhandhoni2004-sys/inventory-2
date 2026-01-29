package com.example.inventory_api.security;
 
import io.swagger.v3.oas.models.OpenAPI;

import io.swagger.v3.oas.models.info.Info;

import io.swagger.v3.oas.models.security.SecurityRequirement;

import io.swagger.v3.oas.models.security.SecurityScheme;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
 
@Configuration

public class Security {
 
    @Bean

    public OpenAPI openAPI() {

        final String schemeName = "basicAuth";
 
        return new OpenAPI()

                .info(new Info().title("Inventory API").version("v1"))

                .components(new io.swagger.v3.oas.models.Components()

                        .addSecuritySchemes(schemeName,

                                new SecurityScheme()

                                        .type(SecurityScheme.Type.HTTP)

                                        .scheme("basic")))

                .addSecurityItem(new SecurityRequirement().addList(schemeName));

    }

}
 