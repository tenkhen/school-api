package io.khenrab.school.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("School API")
                        .version("1.0.0")
                        .description("API for managing a school system, including students, teachers, courses, classes and enrollments.")
                        .contact(new Contact()
                                .name("Khenrab Dorjee Lama")
                                .email("khenrabdorjee.lama@gmail.com")
                                .url("https://khenrab.io"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")))
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("Local environment"),
                        new Server().url("https://api.khenrab.io").description("Production environment")
                ))
                .externalDocs(new ExternalDocumentation()
                        .description("Project Documentation")
                        .url("https://github.com/your-repo"));
    }
}
