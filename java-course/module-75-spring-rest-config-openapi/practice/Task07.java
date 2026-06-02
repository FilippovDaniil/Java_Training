/**
 * Задача 07 — Модуль 75: МИНИ-ПРОЕКТ «Документированный и версионированный Task Tracker API»
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-web:3.2.x,
 *   org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0 (см. theory.md).
 *
 * ЦЕЛЬ: оформить публичный API Task Tracker «как для людей»: версия в URI,
 *       OpenAPI-документация, @Schema на DTO, настроенная Info и CORS.
 *
 * ЗАДАНИЕ (всё в одном файле):
 *
 *   1) Конфигурация OpenAPI (ApiConfig07):
 *        @Bean OpenAPI с Info(title="Task Tracker API", version="1.0.0", description=...).
 *
 *   2) CORS (ApiConfig07 implements WebMvcConfigurer):
 *        для "/api/**" разрешить origin "https://app.example.com" и основные методы.
 *
 *   3) DTO с @Schema:
 *        TaskDto07(@Schema(...) Long id, @Schema(...) String title, @Schema(...) String status)
 *
 *   4) Контроллер v1 (TaskApiV1_07, @RequestMapping("/api/v1/tasks")):
 *        - GET /api/v1/tasks         @Operation("Список задач") → List<TaskDto07>
 *        - GET /api/v1/tasks/{id}    @Operation("Задача по id")
 *              @ApiResponse(200)/@ApiResponse(404) → TaskDto07 либо ResponseStatusException 404
 *        - POST /api/v1/tasks        @Operation("Создать задачу") @RequestParam title → 201
 *
 *   5) Проверьте:
 *        /swagger-ui.html       — документация с описаниями и примерами
 *        /v3/api-docs            — OpenAPI JSON
 *        /api/v1/tasks           — рабочие эндпоинты
 *
 * АРХИТЕКТУРА:
 *
 *   ApiConfig07 (OpenAPI Info + CORS)
 *        │
 *   TaskApiV1_07 (@Operation/@ApiResponse) ──► TaskDto07 (@Schema) ──► Swagger UI
 *
 * ПОДСКАЗКА: соберите вместе Task01 (версия в URI), Task03 (CORS),
 *   Task04 (@Operation), Task05 (@Schema + Info).
 */
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

@SpringBootApplication
public class Task07 {
    public static void main(String[] args) {
        SpringApplication.run(Task07.class, args);
    }
}

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

// ============================================================
// DTO с документацией
// ============================================================

record TaskDto07(
        /* TODO: @Schema(description = "Идентификатор", example = "42") */ Long id,
        /* TODO: @Schema(description = "Заголовок", example = "Купить кофе") */ String title,
        /* TODO: @Schema(description = "Статус", example = "NEW") */ String status
) {}

// ============================================================
// Версионированный документированный контроллер
// ============================================================

// TODO: @RestController + @RequestMapping("/api/v1/tasks")
class TaskApiV1_07 {

    // TODO: @Operation(summary = "Список задач") + @GetMapping
    public List<TaskDto07> list() {
        // TODO: верните список из 1–2 задач
        return null;
    }

    // TODO: @Operation(summary = "Задача по id") + @ApiResponse(200)/@ApiResponse(404) + @GetMapping("/{id}")
    public TaskDto07 get(@PathVariable Long id) {
        // TODO: если id <= 0 → throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Не найдено")
        // TODO: иначе вернуть new TaskDto07(id, "Купить кофе", "NEW")
        return null;
    }

    // TODO: @Operation(summary = "Создать задачу") + @PostMapping → 201
    public ResponseEntity<TaskDto07> create(@RequestParam String title) {
        return null;
    }
}
