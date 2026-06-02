/**
 * Задача 06 — Модуль 76: MockMvc — заголовки, Content-Type, размер массива
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-web:3.2.x,
 *   org.springframework.boot:spring-boot-starter-test:3.2.x (см. theory.md).
 *
 * Это ТЕСТ-КЛАСС (без main).
 *
 * ЗАДАНИЕ:
 *   1) Класс HeadersTest06 пометьте @WebMvcTest(TaskController06.class).
 *   2) @Autowired MockMvc.
 *   3) Тесты:
 *        listReturnsArrayOfThree():
 *          GET /api/tasks → status().isOk()
 *          + content().contentType(MediaType.APPLICATION_JSON)
 *          + jsonPath("$", hasSize(3))
 *        createSetsLocationHeader():
 *          POST /api/tasks → status().isCreated()
 *          + header().string("Location", "/api/tasks/100")
 *
 * ЦЕЛЬ: проверять не только статус, но и заголовки/тип контента/размер массива.
 *
 * ПОДСКАЗКА: import static org.hamcrest.Matchers.hasSize;
 */
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// --- DTO и контроллер (готовы) ---
record TaskDto06(Long id, String title) {}

@RestController
@RequestMapping("/api/tasks")
class TaskController06 {
    @GetMapping
    public List<TaskDto06> all() {
        return List.of(new TaskDto06(1L, "A"), new TaskDto06(2L, "B"), new TaskDto06(3L, "C"));
    }

    @PostMapping
    public ResponseEntity<TaskDto06> create() {
        return ResponseEntity.created(URI.create("/api/tasks/100")).body(new TaskDto06(100L, "Новая"));
    }
}

// --- ТЕСТ (каркас) ---
// TODO: @WebMvcTest(TaskController06.class)
class HeadersTest06 {

    // TODO: @Autowired MockMvc mockMvc;

    @Test
    void listReturnsArrayOfThree() throws Exception {
        // TODO: mockMvc.perform(get("/api/tasks"))
        //              .andExpect(status().isOk())
        //              .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        //              .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    void createSetsLocationHeader() throws Exception {
        // TODO: mockMvc.perform(post("/api/tasks"))
        //              .andExpect(status().isCreated())
        //              .andExpect(header().string("Location", "/api/tasks/100"));
    }
}
