package dev.matheusferreira.courses_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info; 

@Configuration
public class SwaggerConfig {
  
  @Bean
  public OpenAPI openAPI() {
    return new OpenAPI()
      .info(
        new Info().title("API de Cursos")
          .description("API responsável por fornecer um CRUD de cursos.")
          .version("0.0.1")
      );
  }

}
