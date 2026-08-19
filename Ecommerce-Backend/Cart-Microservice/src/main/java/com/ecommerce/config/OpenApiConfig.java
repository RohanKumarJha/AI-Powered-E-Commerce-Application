package com.ecommerce.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Cart Microservice API")
                        .version("1.0.0")
                        .description("Rest APIs for Cart Microservice of AI Powered E-commerce Application")
                        .contact(new Contact()
                                .name("Rohan Kumar Jha")
                                .email("rohan.jha0107@gmail.com")
                                .url("https://github.com/RohanKumarJha"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")));
    }
}
