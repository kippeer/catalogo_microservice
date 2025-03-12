package com.example.productcatalog.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    
    @Bean
    public OpenAPI productCatalogOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Product Catalog API")
                .description("REST API for managing product catalog")
                .version("1.0"));
    }
}