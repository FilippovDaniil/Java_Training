package m75_spring_rest_config_openapi.practice.task05;

/**
 * Задача 05 — Модуль 75: @Schema на DTO и настройка OpenAPI (Info)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-web:3.2.x,
 *   org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0 (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   1) Разметьте поля TaskDto05 аннотациями @Schema (описание + пример):
 *        id    → @Schema(description = "Идентификатор", example = "42")
 *        title → @Schema(description = "Заголовок задачи", example = "Купить кофе")
 *   2) В классе OpenApiConfig05 объявите @Bean OpenAPI с общей информацией:
 *        new OpenAPI().info(new Info().title("Task Tracker API")
 *                                     .version("1.0.0")
 *                                     .description("API управления задачами"))
 *   3) Откройте /swagger-ui.html — проверьте заголовок и описания полей.
 *
 * ЦЕЛЬ: обогатить схему данных и общую информацию об API в документации.
 *
 * ПОДСКАЗКА:
 *   import io.swagger.v3.oas.annotations.media.Schema;
 *   import io.swagger.v3.oas.models.OpenAPI; import io.swagger.v3.oas.models.info.Info;
 */

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

public class Task05 {
    public static void main(String[] args) {
        SpringApplication.run(Task05.class, args);
    }
}
