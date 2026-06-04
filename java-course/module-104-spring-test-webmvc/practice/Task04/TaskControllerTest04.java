/**
 * Задача 04 — Модуль 104: jsonPath по массивам и вложенным полям
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter-web, spring-boot-starter-test.
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЗАДАНИЕ:
 *   Эндпоинт возвращает список задач (готов). Проверьте структуру ответа через jsonPath:
 *     1) @WebMvcTest(TaskController04.class); @Autowired MockMvc; @MockBean TaskService04 service.
 *     2) list_structure():
 *          when(service.all()).thenReturn(List.of(new TaskDto04(1L,"Кофе"), new TaskDto04(2L,"Чай")));
 *          mockMvc.perform(get("/api/tasks"))
 *                 .andExpect(status().isOk())
 *                 .andExpect(jsonPath("$").isArray())
 *                 .andExpect(jsonPath("$.length()").value(2))
 *                 .andExpect(jsonPath("$[0].title").value("Кофе"))
 *                 .andExpect(jsonPath("$[1].id").value(2))
 *                 .andExpect(jsonPath("$", hasSize(2)));   // Hamcrest-матчер (модуль 101)
 *
 * ЦЕЛЬ: научиться проверять массивы, индексы и размер в JSON-ответе.
 *
 * ВАЖНО: $.length() и hasSize(...) — два способа проверить размер массива.
 *
 * ПОДСКАЗКА: $[i].field — доступ к полю i-го элемента массива.
 */

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// --- ТЕСТ (каркас) ---
// TODO: @WebMvcTest(TaskController04.class)
class TaskControllerTest04 {

    // TODO: @Autowired MockMvc mockMvc;
    // TODO: @MockBean TaskService04 service;

    @Test
    void list_structure() throws Exception {
        // TODO: when(service.all()).thenReturn(List.of(new TaskDto04(1L, "Кофе"), new TaskDto04(2L, "Чай")));
        // TODO: mockMvc.perform(get("/api/tasks"))
        //              .andExpect(status().isOk())
        //              .andExpect(jsonPath("$").isArray())
        //              .andExpect(jsonPath("$.length()").value(2))
        //              .andExpect(jsonPath("$[0].title").value("Кофе"))
        //              .andExpect(jsonPath("$[1].id").value(2))
        //              .andExpect(jsonPath("$", hasSize(2)));
    }
}
