/**
 * Задача 02 — Модуль 76: @WebMvcTest — POST с JSON-телом → 201
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-web:3.2.x,
 *   org.springframework.boot:spring-boot-starter-test:3.2.x (см. theory.md).
 *
 * Это ТЕСТ-КЛАСС (без main).
 *
 * ЗАДАНИЕ:
 *   1) Класс CreateTaskTest02 пометьте @WebMvcTest(TaskController02.class).
 *   2) Внедрите @Autowired MockMvc и @Autowired ObjectMapper.
 *   3) В тесте createReturns201():
 *        - тело: objectMapper.writeValueAsString(new CreateTaskDto02("Купить кофе"))
 *        - mockMvc.perform(post("/api/tasks")
 *                    .contentType(MediaType.APPLICATION_JSON)
 *                    .content(json))
 *                 .andExpect(status().isCreated())
 *                 .andExpect(header().exists("Location"))
 *                 .andExpect(jsonPath("$.title").value("Купить кофе"));
 *
 * ЦЕЛЬ: проверить создание ресурса — статус 201, заголовок Location, тело.
 *
 * ПОДСКАЗКА: ObjectMapper доступен как бин в контексте @WebMvcTest.
 */
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// --- DTO и контроллер (готовы) ---
record CreateTaskDto02(String title) {}
record TaskView02(Long id, String title) {}

@RestController
@RequestMapping("/api/tasks")
class TaskController02 {
    @PostMapping
    public ResponseEntity<TaskView02> create(@RequestBody CreateTaskDto02 dto) {
        TaskView02 saved = new TaskView02(1L, dto.title());
        return ResponseEntity.created(URI.create("/api/tasks/1")).body(saved);
    }
}

// --- ТЕСТ (каркас) ---
// TODO: @WebMvcTest(TaskController02.class)
class CreateTaskTest02 {

    // TODO: @Autowired MockMvc mockMvc;
    // TODO: @Autowired ObjectMapper objectMapper;

    @Test
    void createReturns201() throws Exception {
        // TODO: String json = objectMapper.writeValueAsString(new CreateTaskDto02("Купить кофе"));
        // TODO: mockMvc.perform(post("/api/tasks").contentType(MediaType.APPLICATION_JSON).content(json))
        //              .andExpect(status().isCreated())
        //              .andExpect(header().exists("Location"))
        //              .andExpect(jsonPath("$.title").value("Купить кофе"));
    }
}
