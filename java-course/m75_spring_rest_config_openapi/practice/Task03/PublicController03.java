package m75_spring_rest_config_openapi.practice.task03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// TODO: @CrossOrigin(origins = "https://app.example.com")
@RestController
@RequestMapping("/api/public")
class PublicController03 {
    // TODO: @GetMapping("/ping") → "pong"
    public String ping() {
        return null;
    }
}
