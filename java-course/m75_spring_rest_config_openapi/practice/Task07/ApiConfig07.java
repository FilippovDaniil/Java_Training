package m75_spring_rest_config_openapi.practice.task07;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.util.List;

// ============================================================
// Конфигурация: OpenAPI Info + CORS
// ============================================================

@Configuration
class ApiConfig07 implements WebMvcConfigurer {

    // TODO: @Bean OpenAPI apiInfo() { return new OpenAPI().info(new Info()....); }
    public OpenAPI apiInfo() {
        return null;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // TODO: registry.addMapping("/api/**").allowedOrigins("https://app.example.com")
        //               .allowedMethods("GET","POST","PUT","PATCH","DELETE");
    }
}
