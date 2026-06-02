/**
 * Задача 07 — Модуль 76: МИНИ-ПРОЕКТ «Полный набор тестов контроллера Task Tracker»
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-web:3.2.x,
 *   org.springframework.boot:spring-boot-starter-validation:3.2.x,
 *   org.springframework.boot:spring-boot-starter-test:3.2.x (см. theory.md).
 *
 * Это ТЕСТ-КЛАСС (без main). Завершает блок Spring REST (67–76).
 *
 * ЦЕЛЬ: покрыть контроллер Task Tracker полноценным slice-тестом — все ключевые
 *       сценарии: список, чтение (200/404), создание (201), валидация (400), удаление (204).
 *
 * ДАНО: TaskService07 (интерфейс), TaskController07, GlobalHandler07 — готовы.
 *       Контроллер бросает TaskNotFoundException07 (→404), валидирует тело (→400).
 *
 * ЗАДАНИЕ — реализуйте тесты в TaskControllerTest07:
 *   1) Аннотируйте класс: @WebMvcTest(TaskController07.class) + @Import(GlobalHandler07.class).
 *   2) @Autowired MockMvc, @Autowired ObjectMapper, @MockBean TaskService07.
 *   3) Тесты:
 *        listReturnsAll():       GET /api/tasks → 200 + jsonPath("$", hasSize(2))
 *        getExistingReturns200(): when(service.find(1L)).thenReturn(...);
 *                                 GET /api/tasks/1 → 200 + jsonPath("$.title")
 *        getMissingReturns404():  when(service.find(999L)).thenThrow(TaskNotFoundException07);
 *                                 GET /api/tasks/999 → 404
 *        createReturns201():      POST валидное тело → 201 + header Location
 *        createInvalidReturns400(): POST {"title":""} → 400
 *        deleteReturns204():      DELETE /api/tasks/1 → 204
 *
 * ЦЕЛЬ-ИТОГ: единый тест-класс, проверяющий контракт контроллера целиком.
 *
 * ПОДСКАЗКА: соберите приёмы Task01–Task04 и Task06.
 */
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// ============================================================
// Готовые компоненты под тест
// ============================================================

record TaskDto07(Long id, String title, String status) {}
record CreateTaskDto07(@NotBlank @Size(min = 3, max = 100) String title) {}

class TaskNotFoundException07 extends RuntimeException {
    TaskNotFoundException07(Long id) { super("Задача " + id + " не найдена"); }
}

interface TaskService07 {
    List<TaskDto07> findAll();
    TaskDto07 find(Long id);
    TaskDto07 create(String title);
    void delete(Long id);
}

@RestController
@RequestMapping("/api/tasks")
class TaskController07 {
    private final TaskService07 service;
    TaskController07(TaskService07 service) { this.service = service; }

    @GetMapping
    public List<TaskDto07> all() { return service.findAll(); }

    @GetMapping("/{id}")
    public TaskDto07 one(@PathVariable Long id) { return service.find(id); }

    @PostMapping
    public ResponseEntity<TaskDto07> create(@Valid @RequestBody CreateTaskDto07 dto) {
        TaskDto07 saved = service.create(dto.title());
        return ResponseEntity.created(URI.create("/api/tasks/" + saved.id())).body(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

@RestControllerAdvice
class GlobalHandler07 {
    @ExceptionHandler(TaskNotFoundException07.class)
    public ResponseEntity<Map<String, Object>> handle(TaskNotFoundException07 ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", ex.getMessage()));
    }
}

// ============================================================
// ТЕСТ (каркас — реализуйте все методы)
// ============================================================

// TODO: @WebMvcTest(TaskController07.class)
// TODO: @Import(GlobalHandler07.class)
class TaskControllerTest07 {

    // TODO: @Autowired MockMvc mockMvc;
    // TODO: @Autowired ObjectMapper objectMapper;
    // TODO: @MockBean TaskService07 service;

    @Test
    void listReturnsAll() throws Exception {
        // TODO: when(service.findAll()).thenReturn(List.of(new TaskDto07(1L,"A","NEW"), new TaskDto07(2L,"B","DONE")));
        // TODO: GET /api/tasks → status().isOk() + jsonPath("$", hasSize(2))
    }

    @Test
    void getExistingReturns200() throws Exception {
        // TODO: when(service.find(1L)).thenReturn(new TaskDto07(1L,"Кофе","NEW"));
        // TODO: GET /api/tasks/1 → 200 + jsonPath("$.title").value("Кофе")
    }

    @Test
    void getMissingReturns404() throws Exception {
        // TODO: when(service.find(999L)).thenThrow(new TaskNotFoundException07(999L));
        // TODO: GET /api/tasks/999 → status().isNotFound()
    }

    @Test
    void createReturns201() throws Exception {
        // TODO: when(service.create("Купить кофе")).thenReturn(new TaskDto07(5L,"Купить кофе","NEW"));
        // TODO: POST валидное тело → status().isCreated() + header().exists("Location")
    }

    @Test
    void createInvalidReturns400() throws Exception {
        // TODO: POST {"title":""} → status().isBadRequest()
    }

    @Test
    void deleteReturns204() throws Exception {
        // TODO: DELETE /api/tasks/1 → status().isNoContent()
    }
}
