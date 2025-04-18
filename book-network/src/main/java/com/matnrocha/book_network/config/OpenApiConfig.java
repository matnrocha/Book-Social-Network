package com.matnrocha.book_network.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Mateus Rocha",
                        email = "mateusrocha.manr@gmail.com",
                        url = "https://github.com/matnrocha/matnrocha.github.io"
                ),
                description = "OpenApi documentation for backend Book-social-network project",
                title = "book social network documentation",
                version = "1.0"
        ),
        servers = @Server(
                description = "Local Environment",
                url = "http://localhost:8088/api/v1"
                //here it is possible to concatenate many servers like PROD and give links
        ),
        security = {
                @SecurityRequirement(
                        name = "bearerAuth"
                )
        }
)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT auth description",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {

}
