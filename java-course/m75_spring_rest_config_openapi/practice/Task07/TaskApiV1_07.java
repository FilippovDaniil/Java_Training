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
