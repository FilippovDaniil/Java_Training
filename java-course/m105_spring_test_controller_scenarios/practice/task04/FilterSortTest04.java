package m105_spring_test_controller_scenarios.practice.task04;

/**
 * Задача 04 — Модуль 105: фильтрация и сортировка через query-параметры
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter-web, spring-boot-starter-test.
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЗАДАНИЕ:
 *   Контроллер фильтрует по status и сортирует по sort (готов). Проверьте передачу параметров:
 *     1) @WebMvcTest(TaskController04.class); @Autowired MockMvc; @MockBean TaskService04 service.
 *     2) filters_by_status():
 *          when(service.search("NEW", "title")).thenReturn(List.of(new TaskDto04(1L,"Кофе","NEW")));
 *          mockMvc.perform(get("/api/tasks").param("status","NEW").param("sort","title"))
 *                 .andExpect(status().isOk())
 *                 .andExpect(jsonPath("$.length()").value(1))
 *                 .andExpect(jsonPath("$[0].status").value("NEW"));
 *          verify(service).search("NEW", "title");   // параметры дошли до сервиса
 *     3) default_params():
 *          when(service.search(null, "id")).thenReturn(List.of());
 *          mockMvc.perform(get("/api/tasks")).andExpect(status().isOk());   // дефолты применились
 *
 * ЦЕЛЬ: убедиться, что @RequestParam (с дефолтами) корректно передаются в сервис.
 *
 * ВАЖНО: проверяйте не только ответ, но и какие аргументы получил сервис (verify).
 *
 * ПОДСКАЗКА: status необязателен (required=false), sort имеет defaultValue="id".
 */

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// --- ТЕСТ (каркас) ---
// TODO: @WebMvcTest(TaskController04.class)
class FilterSortTest04 {

    // TODO: @Autowired MockMvc mockMvc;
    // TODO: @MockBean TaskService04 service;

    @Test
    void filters_by_status() throws Exception {
        // TODO: when(service.search("NEW", "title")).thenReturn(List.of(new TaskDto04(1L, "Кофе", "NEW")));
        // TODO: mockMvc.perform(get("/api/tasks").param("status", "NEW").param("sort", "title"))
        //              .andExpect(status().isOk())
        //              .andExpect(jsonPath("$.length()").value(1))
        //              .andExpect(jsonPath("$[0].status").value("NEW"));
        // TODO: verify(service).search("NEW", "title");
    }

    @Test
    void default_params() throws Exception {
        // TODO: when(service.search(null, "id")).thenReturn(List.of());
        // TODO: mockMvc.perform(get("/api/tasks")).andExpect(status().isOk());
    }
}
