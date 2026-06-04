/**
 * Задача 07 — Модуль 105: МИНИ-ПРОЕКТ «Сценарный тест-сьют контроллера Task Tracker»
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-web, spring-boot-starter-validation, spring-boot-starter-test.
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЦЕЛЬ: одним сьютом покрыть типовые сценарии REST: валидация (400), ошибка (404 ProblemDetail),
 *       пагинация и загрузка файла. Капстоун первой части Spring Test (101–105).
 *
 * КОНТРОЛЛЕР TaskController07 + advice (готовы ниже):
 *   POST /api/tasks                       → @Valid (400 при пустом title), иначе 201;
 *   GET  /api/tasks/{id}                  → 404 (TaskNotFound07 → advice → ProblemDetail);
 *   GET  /api/tasks                       → Page<TaskDto07>;
 *   POST /api/tasks/{id}/attachments      → multipart upload, 201.
 *
 * ЗАДАНИЕ — @WebMvcTest(TaskController07.class) + @Import(Advice07.class),
 *           @Autowired MockMvc, @MockBean TaskService07:
 *
 *   1) create_blank_400(): POST {"title":""} → isBadRequest(); verify(service, never()).create(any()).
 *   2) create_ok_201():    when(service.create(any())).thenReturn(1L);
 *                          POST {"title":"Кофе"} → isCreated().
 *   3) get_missing_404():  when(service.find(99L)).thenThrow(new TaskNotFound07(99L));
 *                          GET /api/tasks/99 → isNotFound(), jsonPath("$.status").value(404).
 *   4) list_paged():       when(service.list(any())).thenReturn(
 *                              new PageImpl<>(List.of(new TaskDto07(1L,"Кофе")), PageRequest.of(0,10), 1));
 *                          GET /api/tasks?page=0&size=10 → isOk(), $.totalElements = 1.
 *   5) upload_201():       MockMultipartFile file = new MockMultipartFile("file","n.txt","text/plain","x".getBytes());
 *                          when(service.store(any())).thenReturn("n.txt");
 *                          multipart("/api/tasks/1/attachments").file(file) → isCreated(), $.filename="n.txt".
 *
 * ОЖИДАЕМЫЙ ИТОГ: ключевые сценарии контроллера зафиксированы; регрессия в контракте будет поймана.
 *
 * ПОДСКАЗКА: соедините задачи 01 (валидация), 02 (ошибка), 03 (пагинация), 05 (upload).
 */
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// --- модель, сервис, контроллер, advice (готовы) ---
record CreateTask07(@NotBlank String title) {}
record TaskDto07(Long id, String title) {}

class TaskNotFound07 extends RuntimeException {
    TaskNotFound07(Long id) { super("Задача не найдена: " + id); }
}

@Service
class TaskService07 {
    public Long create(CreateTask07 req) { return 1L; }
    public TaskDto07 find(Long id) { return new TaskDto07(id, "?"); }
    public Page<TaskDto07> list(Pageable pageable) { return Page.empty(); }
    public String store(MultipartFile file) { return file.getOriginalFilename(); }
}

@RestController
@RequestMapping("/api/tasks")
class TaskController07 {
    private final TaskService07 service;
    TaskController07(TaskService07 service) { this.service = service; }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@Valid @RequestBody CreateTask07 req) { return service.create(req); }

    @GetMapping("/{id}")
    public TaskDto07 get(@PathVariable Long id) { return service.find(id); }

    @GetMapping
    public Page<TaskDto07> list(Pageable pageable) { return service.list(pageable); }

    @PostMapping("/{id}/attachments")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, String> upload(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        return Map.of("filename", service.store(file));
    }
}

@RestControllerAdvice
class Advice07 {
    @ExceptionHandler(TaskNotFound07.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ProblemDetail notFound(TaskNotFound07 e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
    }
}

// --- ТЕСТ (каркас) ---
// TODO: @WebMvcTest(TaskController07.class)
// TODO: @Import(Advice07.class)
class ScenarioSuite07 {

    // TODO: @Autowired MockMvc mockMvc;
    // TODO: @MockBean TaskService07 service;

    @Test
    void create_blank_400() throws Exception {
        // TODO: mockMvc.perform(post("/api/tasks").contentType(APPLICATION_JSON).content("{\"title\":\"\"}"))
        //              .andExpect(status().isBadRequest());
        // TODO: verify(service, never()).create(any());
    }

    @Test
    void create_ok_201() throws Exception {
        // TODO: when(service.create(any())).thenReturn(1L);
        // TODO: mockMvc.perform(post("/api/tasks").contentType(APPLICATION_JSON).content("{\"title\":\"Кофе\"}"))
        //              .andExpect(status().isCreated());
    }

    @Test
    void get_missing_404() throws Exception {
        // TODO: when(service.find(99L)).thenThrow(new TaskNotFound07(99L));
        // TODO: mockMvc.perform(get("/api/tasks/99"))
        //              .andExpect(status().isNotFound())
        //              .andExpect(jsonPath("$.status").value(404));
    }

    @Test
    void list_paged() throws Exception {
        // TODO: when(service.list(any())).thenReturn(
        // TODO:     new PageImpl<>(List.of(new TaskDto07(1L, "Кофе")), PageRequest.of(0, 10), 1));
        // TODO: mockMvc.perform(get("/api/tasks").param("page", "0").param("size", "10"))
        //              .andExpect(status().isOk())
        //              .andExpect(jsonPath("$.totalElements").value(1));
    }

    @Test
    void upload_201() throws Exception {
        // TODO: MockMultipartFile file = new MockMultipartFile("file", "n.txt", "text/plain", "x".getBytes());
        // TODO: when(service.store(any())).thenReturn("n.txt");
        // TODO: mockMvc.perform(multipart("/api/tasks/1/attachments").file(file))
        //              .andExpect(status().isCreated())
        //              .andExpect(jsonPath("$.filename").value("n.txt"));
    }
}
