/**
 * Задача 03 — Модуль 105: тест пагинации (Page + параметры page/size/sort)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-web, spring-boot-starter-test, spring-data-commons (Page/Pageable).
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЗАДАНИЕ:
 *   Контроллер отдаёт Page<TaskDto03> (готов). Проверьте пагинированный ответ:
 *     1) @WebMvcTest(TaskController03.class); @Autowired MockMvc; @MockBean TaskService03 service.
 *     2) paginated_response():
 *          Page<TaskDto03> page = new PageImpl<>(List.of(new TaskDto03(1L,"Кофе")),
 *                                                PageRequest.of(0,10), 1);
 *          when(service.list(any(Pageable.class))).thenReturn(page);
 *          mockMvc.perform(get("/api/tasks").param("page","0").param("size","10").param("sort","title,asc"))
 *                 .andExpect(status().isOk())
 *                 .andExpect(jsonPath("$.content").isArray())
 *                 .andExpect(jsonPath("$.content.length()").value(1))
 *                 .andExpect(jsonPath("$.totalElements").value(1))
 *                 .andExpect(jsonPath("$.number").value(0));
 *
 * ЦЕЛЬ: тестировать query-параметры пагинации и метаданные страницы (content/totalElements/number).
 *
 * ВАЖНО: any(Pageable.class) — контроллер сам собирает Pageable из page/size/sort.
 *
 * ПОДСКАЗКА: структура ответа Page: content[], totalElements, number, size.
 */
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// --- DTO, сервис, контроллер (готовы) ---
record TaskDto03(Long id, String title) {}

@Service
class TaskService03 {
    public Page<TaskDto03> list(Pageable pageable) { return Page.empty(); }
}

@RestController
@RequestMapping("/api/tasks")
class TaskController03 {
    private final TaskService03 service;
    TaskController03(TaskService03 service) { this.service = service; }

    @GetMapping
    public Page<TaskDto03> list(Pageable pageable) { return service.list(pageable); }
}

// --- ТЕСТ (каркас) ---
// TODO: @WebMvcTest(TaskController03.class)
class PaginationTest03 {

    // TODO: @Autowired MockMvc mockMvc;
    // TODO: @MockBean TaskService03 service;

    @Test
    void paginated_response() throws Exception {
        // TODO: Page<TaskDto03> page = new PageImpl<>(List.of(new TaskDto03(1L, "Кофе")),
        // TODO:         PageRequest.of(0, 10), 1);
        // TODO: when(service.list(any(Pageable.class))).thenReturn(page);
        // TODO: mockMvc.perform(get("/api/tasks").param("page", "0").param("size", "10").param("sort", "title,asc"))
        //              .andExpect(status().isOk())
        //              .andExpect(jsonPath("$.content").isArray())
        //              .andExpect(jsonPath("$.content.length()").value(1))
        //              .andExpect(jsonPath("$.totalElements").value(1))
        //              .andExpect(jsonPath("$.number").value(0));
    }
}
