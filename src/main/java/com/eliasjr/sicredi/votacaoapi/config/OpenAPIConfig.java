package com.eliasjr.sicredi.votacaoapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI myApi() {

        Contact contact = new Contact();
        contact.setEmail("elias.dvlpr@gmail.com");
        contact.setName("Elias Junior");
        contact.setUrl("https://www.linkedin.com/in/elias-ara%C3%BAjo-20b677a5/");

        return new OpenAPI().info(new Info()
                .title("REST Api")
                .version("1.0")
                .contact(contact)
                .description("Essa API expõe endpoints para gerenciar sessões de votação."));
    }
}
