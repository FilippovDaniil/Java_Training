package m75_spring_rest_config_openapi.practice.task07;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

// ============================================================
// Версионированный документированный контроллер
// ============================================================

@RestController
@RequestMapping("/api/v1/tasks")
@Tag(name = "Task API", description = "API для управления задачами")
class TaskApiV1_07 {

    private final ConcurrentHashMap<Long, TaskDto07> store = new ConcurrentHashMap<>();
    private final AtomicLong seq = new AtomicLong(1);

    // Инициализация с тестовыми данными
    {
        store.put(1L, new TaskDto07(1L, "Купить кофе", "NEW"));
        store.put(2L, new TaskDto07(2L, "Отчёт", "IN_PROGRESS"));
    }


    @Operation(
            summary = "Список задач",
            description = "Возвращает список всех задач. Можно использовать фильтры.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Успешный ответ",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = TaskDto07.class))
                            )
                    )
            }
    )
    @GetMapping
    public List<TaskDto07> list(
            @RequestParam(required = false)
            @Parameter(description = "Фильтр по статусу", example = "NEW")
            String status) {

        if (status != null && !status.isBlank()) {
            return store.values().stream()
                    .filter(task -> task.status().equals(status))
                    .toList();
        }
        return List.copyOf(store.values());
    }

    @Operation(
            summary = "Задача по id",
            description = "Возвращает задачу по её идентификатору. Если задача не найдена - 404."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Задача не найдена")
    })
    @GetMapping("/{id}")
    public TaskDto07 get(@PathVariable Long id) {
        TaskDto07 task = store.get(id);
        if (task == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Задача не найдена");
        }
        return task;
    }

    @Operation(
            summary = "Создать задачу",
            description = "Создаёт новую задачу с указанным заголовком и статусом NEW"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Создано"),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос")
    })
    @PostMapping
    public ResponseEntity<TaskDto07> create(
            @RequestParam
            @Parameter(description = "Заголовок задачи", required = true, example = "Купить кофе")
            String title) {

        Long id = seq.getAndIncrement();
        TaskDto07 task = new TaskDto07(id, title, "NEW");
        store.put(id, task);

        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }
}
