/**
 * Задача 07 — Модуль 104: МИНИ-ПРОЕКТ «Полный slice-тест контроллера Task Tracker»
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter-web, spring-boot-starter-test.
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЦЕЛЬ: покрыть @WebMvcTest'ом весь CRUD контроллера задач: список, чтение по id (200 и 404),
 *       создание (201 + Location). Сервис замокан, БД нет.
 *
 * КОНТРОЛЛЕР TaskController07 (готов ниже):
 *   GET  /api/tasks        → список;
 *   GET  /api/tasks/{id}   → задача или 404 (сервис бросает NoSuchElementException → advice → 404);
 *   POST /api/tasks        → 201 + Location.
 *
 * ЗАДАНИЕ — напишите тест-класс @WebMvcTest(TaskController07.class)
 *           @Import(TaskAdvice07.class) с @Autowired MockMvc, @Autowired ObjectMapper, @MockBean TaskService07:
 *
 *   1) lists_all():
 *        when(service.all()).thenReturn(List.of(new TaskDto07(1L,"Кофе","NEW")));
 *        get("/api/tasks") → isOk(), jsonPath("$.length()").value(1), $[0].title = "Кофе".
 *
 *   2) gets_one():
 *        when(service.find(1L)).thenReturn(new TaskDto07(1L,"Кофе","NEW"));
 *        get("/api/tasks/1") → isOk(), $.status = "NEW".
 *
 *   3) get_missing_404():
 *        when(service.find(99L)).thenThrow(new NoSuchElementException("нет"));
 *        get("/api/tasks/99") → isNotFound().
 *
 *   4) creates_201_location():
 *        when(service.create(any())).thenReturn(new TaskDto07(10L,"Чай","NEW"));
 *        post("/api/tasks") c JSON {"title":"Чай"} → isCreated(), header Location "/api/tasks/10", $.id = 10.
 *
 * ОЖИДАЕМЫЙ ИТОГ: весь web-контракт контроллера зафиксирован быстрыми срезовыми тестами.
 *
 * ПОДСКАЗКА: соедините задачи 01 (GET), 02 (POST+Location), 04 (массив), 03 (verify при желании).
 */
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// --- DTO, сервис, контроллер, advice (готовы) ---
record CreateTask07(String title) {}
record TaskDto07(Long id, String title, String status) {}

@Service
class TaskService07 {
    public List<TaskDto07> all() { return List.of(); }
    public TaskDto07 find(Long id) { return new TaskDto07(id, "?", "?"); }
    public TaskDto07 create(CreateTask07 req) { return new TaskDto07(0L, req.title(), "NEW"); }
}

@RestController
@RequestMapping("/api/tasks")
class TaskController07 {
    private final TaskService07 service;
    TaskController07(TaskService07 service) { this.service = service; }

    @GetMapping
    public List<TaskDto07> all() { return service.all(); }

    @GetMapping("/{id}")
    public TaskDto07 get(@PathVariable Long id) { return service.find(id); }

    @PostMapping
    public ResponseEntity<TaskDto07> create(@RequestBody CreateTask07 req) {
        TaskDto07 c = service.create(req);
        return ResponseEntity.created(URI.create("/api/tasks/" + c.id())).body(c);
    }
}

@RestControllerAdvice
class TaskAdvice07 {
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ProblemDetail notFound(NoSuchElementException e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
    }
}

// --- ТЕСТ (каркас) ---
// TODO: @WebMvcTest(TaskController07.class)
// TODO: @Import(TaskAdvice07.class)
class TaskControllerSliceSuite07 {

    // TODO: @Autowired MockMvc mockMvc;
    // TODO: @Autowired ObjectMapper objectMapper;
    // TODO: @MockBean TaskService07 service;

    @Test
    void lists_all() throws Exception {
        // TODO: when(service.all()).thenReturn(List.of(new TaskDto07(1L, "Кофе", "NEW")));
        // TODO: mockMvc.perform(get("/api/tasks"))
        //              .andExpect(status().isOk())
        //              .andExpect(jsonPath("$.length()").value(1))
        //              .andExpect(jsonPath("$[0].title").value("Кофе"));
    }

    @Test
    void gets_one() throws Exception {
        // TODO: when(service.find(1L)).thenReturn(new TaskDto07(1L, "Кофе", "NEW"));
        // TODO: mockMvc.perform(get("/api/tasks/1"))
        //              .andExpect(status().isOk())
        //              .andExpect(jsonPath("$.status").value("NEW"));
    }

    @Test
    void get_missing_404() throws Exception {
        // TODO: when(service.find(99L)).thenThrow(new NoSuchElementException("нет"));
        // TODO: mockMvc.perform(get("/api/tasks/99")).andExpect(status().isNotFound());
    }

    @Test
    void creates_201_location() throws Exception {
        // TODO: when(service.create(any())).thenReturn(new TaskDto07(10L, "Чай", "NEW"));
        // TODO: String body = objectMapper.writeValueAsString(new CreateTask07("Чай"));
        // TODO: mockMvc.perform(post("/api/tasks").contentType(APPLICATION_JSON).content(body))
        //              .andExpect(status().isCreated())
        //              .andExpect(header().string("Location", "/api/tasks/10"))
        //              .andExpect(jsonPath("$.id").value(10));
    }
}
