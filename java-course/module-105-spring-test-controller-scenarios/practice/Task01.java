/**
 * Задача 01 — Модуль 105: тест валидации (@Valid → 400) + сервис не вызывается
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-web, spring-boot-starter-validation, spring-boot-starter-test.
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЗАДАНИЕ:
 *   Контроллер принимает CreateTask01 с @NotBlank title (готов). Проверьте отказ невалидного:
 *     1) @WebMvcTest(TaskController01.class); @Autowired MockMvc; @MockBean TaskService01 service.
 *     2) blank_title_400():
 *          post("/api/tasks") с телом {"title":""} → status().isBadRequest();
 *          verify(service, never()).create(any());   // до бизнес-логики не дошло.
 *     3) valid_title_201():
 *          when(service.create(any())).thenReturn(1L);
 *          post с {"title":"Кофе"} → status().isCreated().
 *
 * ЦЕЛЬ: убедиться, что Bean Validation отсекает плохой ввод (400) ДО вызова сервиса.
 *
 * ВАЖНО: при провале валидации контроллер бросает MethodArgumentNotValidException → 400.
 *
 * ПОДСКАЗКА: never() подтверждает, что невалидный запрос не дошёл до service.create.
 */
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// --- DTO, сервис, контроллер (готовы) ---
record CreateTask01(@NotBlank String title) {}

@Service
class TaskService01 {
    public Long create(CreateTask01 req) { return 1L; }
}

@RestController
@RequestMapping("/api/tasks")
class TaskController01 {
    private final TaskService01 service;
    TaskController01(TaskService01 service) { this.service = service; }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@Valid @RequestBody CreateTask01 req) { return service.create(req); }
}

// --- ТЕСТ (каркас) ---
// TODO: @WebMvcTest(TaskController01.class)
class ValidationTest01 {

    // TODO: @Autowired MockMvc mockMvc;
    // TODO: @MockBean TaskService01 service;

    @Test
    void blank_title_400() throws Exception {
        // TODO: mockMvc.perform(post("/api/tasks").contentType(APPLICATION_JSON).content("{\"title\":\"\"}"))
        //              .andExpect(status().isBadRequest());
        // TODO: verify(service, never()).create(any());
    }

    @Test
    void valid_title_201() throws Exception {
        // TODO: when(service.create(any())).thenReturn(1L);
        // TODO: mockMvc.perform(post("/api/tasks").contentType(APPLICATION_JSON).content("{\"title\":\"Кофе\"}"))
        //              .andExpect(status().isCreated());
    }
}
