package m75_spring_rest_config_openapi.practice.task04;

/**
 * Задача 04 — Модуль 75: Документирование эндпоинтов через OpenAPI (@Operation)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-web:3.2.x,
 *   org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0 (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Опишите эндпоинт аннотациями OpenAPI, чтобы он красиво выглядел в Swagger UI.
 *   1) Над методом get добавьте:
 *        @Operation(summary = "Получить задачу", description = "Возвращает задачу по id или 404")
 *        @ApiResponse(responseCode = "200", description = "Задача найдена")
 *        @ApiResponse(responseCode = "404", description = "Задача не найдена")
 *   2) Реализуйте GET /api/tasks/{id} → TaskDto04 (или ResponseStatusException 404 при id<=0).
 *   3) Запустите и откройте:
 *        http://localhost:8080/swagger-ui.html      — интерактивная документация
 *        http://localhost:8080/v3/api-docs            — JSON OpenAPI
 *
 * ЦЕЛЬ: увидеть автогенерируемую документацию и обогатить её аннотациями.
 *
 * ПОДСКАЗКА:
 *   import io.swagger.v3.oas.annotations.Operation;
 *   import io.swagger.v3.oas.annotations.responses.ApiResponse;
 */

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

public class Task04 {
    public static void main(String[] args) {
        SpringApplication.run(Task04.class, args);
    }
}
