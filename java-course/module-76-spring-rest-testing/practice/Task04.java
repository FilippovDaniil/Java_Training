/**
 * Задача 04 — Модуль 76: @WebMvcTest — проверка валидации (400)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-web:3.2.x,
 *   org.springframework.boot:spring-boot-starter-validation:3.2.x,
 *   org.springframework.boot:spring-boot-starter-test:3.2.x (см. theory.md).
 *
 * Это ТЕСТ-КЛАСС (без main).
 *
 * ЗАДАНИЕ:
 *   DTO размечен @NotBlank @Size(min=3). Контроллер использует @Valid.
 *   1) Класс ValidationTest04 пометьте @WebMvcTest(TaskController04.class).
 *   2) @Autowired MockMvc.
 *   3) Тесты:
 *        validBodyReturns200(): тело {"title":"Кофе"} → status().isOk()
 *        invalidBodyReturns400(): тело {"title":""}   → status().isBadRequest()
 *
 * ЦЕЛЬ: убедиться, что валидация действительно отклоняет неверные данные (400).
 *
 * ПОДСКАЗКА: тело передавайте строкой в .content("{\"title\":\"...\"}").
 */
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// --- DTO и контроллер (готовы) ---
record CreateTaskDto04(@NotBlank @Size(min = 3) String title) {}

@RestController
@RequestMapping("/api/tasks")
class TaskController04 {
    @PostMapping
    public String create(@Valid @RequestBody CreateTaskDto04 dto) {
        return "OK: " + dto.title();
    }
}

// --- ТЕСТ (каркас) ---
// TODO: @WebMvcTest(TaskController04.class)
class ValidationTest04 {

    // TODO: @Autowired MockMvc mockMvc;

    @Test
    void validBodyReturns200() throws Exception {
        // TODO: mockMvc.perform(post("/api/tasks").contentType(MediaType.APPLICATION_JSON)
        //              .content("{\"title\":\"Кофе\"}"))
        //              .andExpect(status().isOk());
    }

    @Test
    void invalidBodyReturns400() throws Exception {
        // TODO: mockMvc.perform(post("/api/tasks").contentType(MediaType.APPLICATION_JSON)
        //              .content("{\"title\":\"\"}"))
        //              .andExpect(status().isBadRequest());
    }
}
