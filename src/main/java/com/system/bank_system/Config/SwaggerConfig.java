package com.system.bank_system.Config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    public OpenAPI customOpenAPI(){
        return new OpenAPI().info(
            new Info()
                .title("Bank System")
                .description("Bank System Management")
                .version("1.0.0")
                .contact(
                    new Contact()
                        .name("mokhtar")
                        .email("email@gmail.com")
                )
        )
        .addSecurityItem(new SecurityRequirement().addList("Bearer"))
        .components(
            new Components()
                .addSecuritySchemes(
                    "Bearer",
                    new SecurityScheme()
                        .name("Bearer")
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("Bearer")
                        .bearerFormat("JWT")
                        .in(SecurityScheme.In.HEADER)
                )
        );
    }
}