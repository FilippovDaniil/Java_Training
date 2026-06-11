package m104_spring_test_webmvc.practice.task02;

/**
 * Задача 02 — Модуль 104: POST с JSON-телом, статус 201 и заголовок Location
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter-web, spring-boot-starter-test.
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЗАДАНИЕ:
 *   Контроллер создаёт задачу и возвращает 201 + Location (готов ниже). Напишите тест:
 *     1) @WebMvcTest(TaskController02.class); @Autowired MockMvc; @Autowired ObjectMapper;
 *        @MockBean TaskService02 service.
 *     2) createsTask():
 *          when(service.create(any())).thenReturn(new TaskDto02(10L, "Кофе", "NEW"));
 *          String body = objectMapper.writeValueAsString(new CreateTask02("Кофе"));
 *          mockMvc.perform(post("/api/tasks").contentType(APPLICATION_JSON).content(body))
 *                 .andExpect(status().isCreated())
 *                 .andExpect(header().string("Location", "/api/tasks/10"))
 *                 .andExpect(jsonPath("$.id").value(10));
 *
 * ЦЕЛЬ: тестировать запрос с телом, код 201 Created и заголовок Location.
 *
 * ВАЖНО: тело сериализуем ObjectMapper'ом — он автонастроен в @WebMvcTest.
 *
 * ПОДСКАЗКА: any() — т.к. контроллер передаёт сервису объект запроса (точное равенство не важно).
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.net.URI;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// --- ТЕСТ (каркас) ---
// TODO: @WebMvcTest(TaskController02.class)
class TaskControllerTest02 {

    // TODO: @Autowired MockMvc mockMvc;
    // TODO: @Autowired ObjectMapper objectMapper;
    // TODO: @MockBean TaskService02 service;

    @Test
    void createsTask() throws Exception {
        // TODO: when(service.create(any())).thenReturn(new TaskDto02(10L, "Кофе", "NEW"));
        // TODO: String body = objectMapper.writeValueAsString(new CreateTask02("Кофе"));
        // TODO: mockMvc.perform(post("/api/tasks").contentType(APPLICATION_JSON).content(body))
        //              .andExpect(status().isCreated())
        //              .andExpect(header().string("Location", "/api/tasks/10"))
        //              .andExpect(jsonPath("$.id").value(10));
    }
}
