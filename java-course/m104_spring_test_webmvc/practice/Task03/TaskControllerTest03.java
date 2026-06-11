package m104_spring_test_webmvc.practice.task03;

/**
 * Задача 03 — Модуль 104: @MockBean — стабы и verify в slice-тесте
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter-web, spring-boot-starter-test.
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЗАДАНИЕ:
 *   DELETE-эндпоинт делегирует удаление сервису (готов). Проверьте поведение и взаимодействие:
 *     1) @WebMvcTest(TaskController03.class); @Autowired MockMvc; @MockBean TaskService03 service.
 *     2) delete_returns_204_and_calls_service():
 *          mockMvc.perform(delete("/api/tasks/5")).andExpect(status().isNoContent());
 *          verify(service).delete(5L);
 *     3) get_count_uses_stub():
 *          when(service.count()).thenReturn(42L);
 *          mockMvc.perform(get("/api/tasks/count"))
 *                 .andExpect(status().isOk())
 *                 .andExpect(content().string("42"));
 *
 * ЦЕЛЬ: совмещать в slice-тесте стаб (when) и проверку вызова (verify), как в unit-тестах.
 *
 * ВАЖНО: @MockBean можно и стабить (when), и проверять (verify) — это полноценный Mockito-мок.
 *
 * ПОДСКАЗКА: content().string("42") сверяет «сырое» тело ответа.
 */

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// --- ТЕСТ (каркас) ---
// TODO: @WebMvcTest(TaskController03.class)
class TaskControllerTest03 {

    // TODO: @Autowired MockMvc mockMvc;
    // TODO: @MockBean TaskService03 service;

    @Test
    void delete_returns_204_and_calls_service() throws Exception {
        // TODO: mockMvc.perform(delete("/api/tasks/5")).andExpect(status().isNoContent());
        // TODO: verify(service).delete(5L);
    }

    @Test
    void get_count_uses_stub() throws Exception {
        // TODO: when(service.count()).thenReturn(42L);
        // TODO: mockMvc.perform(get("/api/tasks/count"))
        //              .andExpect(status().isOk())
        //              .andExpect(content().string("42"));
    }
}
