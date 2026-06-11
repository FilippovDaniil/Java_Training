package m104_spring_test_webmvc.practice.task01;

/**
 * Задача 01 — Модуль 104: @WebMvcTest + MockMvc — GET (status + jsonPath)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter-web, spring-boot-starter-test.
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЗАДАНИЕ:
 *   Контроллер и сервис готовы (ниже). Напишите slice-тест:
 *     1) @WebMvcTest(TaskController01.class); @Autowired MockMvc; @MockBean TaskService01 service.
 *     2) getReturnsTask():
 *          when(service.find(1L)).thenReturn(new TaskDto01(1L, "Кофе", "NEW"));
 *          mockMvc.perform(get("/api/tasks/1"))
 *                 .andExpect(status().isOk())
 *                 .andExpect(jsonPath("$.title").value("Кофе"))
 *                 .andExpect(jsonPath("$.status").value("NEW"));
 *
 * ЦЕЛЬ: проверить контроллер изолированно (без реального сервиса и БД).
 *
 * ВАЖНО: @MockBean кладёт мок в Spring-контекст — контроллер получит именно его.
 *
 * ПОДСКАЗКА: статические импорты get/status/jsonPath/when уже добавлены.
 */

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
