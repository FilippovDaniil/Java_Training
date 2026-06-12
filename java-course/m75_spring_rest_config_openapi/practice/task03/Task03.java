package m75_spring_rest_config_openapi.practice.task03;

/**
 * Задача 03 — Модуль 75: CORS (точечно и глобально)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework.boot:spring-boot-starter-web:3.2.x (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   1) Точечный CORS: пометьте PublicController03 аннотацией
 *        @CrossOrigin(origins = "https://app.example.com")
 *      и реализуйте GET /api/public/ping → "pong".
 *   2) Глобальный CORS: в классе CorsConfig03 (реализует WebMvcConfigurer)
 *      переопределите addCorsMappings: для "/api/**" разрешите origin
 *      "https://app.example.com" и методы GET, POST, PUT, PATCH, DELETE.
 *
 * ВОПРОС (ответьте комментарием):
 *   Почему allowedOrigins("*") опасно для API с авторизацией?
 *
 * ПОДСКАЗКА:
 *   import org.springframework.web.servlet.config.annotation.*;
 *   CorsRegistry.addMapping("/api/**").allowedOrigins(...).allowedMethods(...).
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class Task03 {
    public static void main(String[] args) {
        SpringApplication.run(Task03.class, args);
    }
}
