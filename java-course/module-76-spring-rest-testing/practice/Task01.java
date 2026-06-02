/**
 * Задача 01 — Модуль 76: @WebMvcTest — GET-эндпоинт (status + jsonPath)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-web:3.2.x,
 *   org.springframework.boot:spring-boot-starter-test:3.2.x (см. theory.md).
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЗАДАНИЕ:
 *   Контроллер и сервис готовы (ниже). Напишите slice-тест:
 *     1) Класс TaskControllerTest01 пометьте @WebMvcTest(TaskController01.class).
 *     2) Внедрите @Autowired MockMvc и объявите @MockBean TaskService01.
 *     3) В тесте getReturnsTask():
 *          - when(service.find(1L)).thenReturn(new TaskDto01(1L, "Кофе", "NEW"));
 *          - mockMvc.perform(get("/api/tasks/1"))
 *                   .andExpect(status().isOk())
 *                   .andExpect(jsonPath("$.title").value("Кофе"))
 *                   .andExpect(jsonPath("$.status").value("NEW"));
 *
 * ЦЕЛЬ: проверить контроллер изолированно, без сервиса и БД.
 *
 * ПОДСКАЗКА: статические импорты get/status/jsonPath уже добавлены.
 */
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.*;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// --- DTO, сервис и контроллер (готовы) ---
record TaskDto01(Long id, String title, String status) {}

@Service
class TaskService01 {
    public TaskDto01 find(Long id) { return new TaskDto01(id, "?", "?"); }
}

@RestController
@RequestMapping("/api/tasks")
class TaskController01 {
    private final TaskService01 service;
    TaskController01(TaskService01 service) { this.service = service; }

    @GetMapping("/{id}")
    public TaskDto01 get(@PathVariable Long id) { return service.find(id); }
}

// --- ТЕСТ (каркас) ---
// TODO: @WebMvcTest(TaskController01.class)
class TaskControllerTest01 {

    // TODO: @Autowired MockMvc mockMvc;
    // TODO: @MockBean TaskService01 service;

    @Test
    void getReturnsTask() throws Exception {
        // TODO: when(service.find(1L)).thenReturn(new TaskDto01(1L, "Кофе", "NEW"));
        // TODO: mockMvc.perform(get("/api/tasks/1"))
        //              .andExpect(status().isOk())
        //              .andExpect(jsonPath("$.title").value("Кофе"))
        //              .andExpect(jsonPath("$.status").value("NEW"));
    }
}
