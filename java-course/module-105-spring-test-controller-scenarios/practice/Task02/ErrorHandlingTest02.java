/**
 * Задача 02 — Модуль 105: обработка ошибок — 404 + ProblemDetail (@Import advice)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter-web, spring-boot-starter-test.
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЗАДАНИЕ:
 *   Сервис бросает TaskNotFound02, advice (готов) превращает это в 404 + ProblemDetail.
 *     1) @WebMvcTest(TaskController02.class) + @Import(GlobalAdvice02.class);
 *        @Autowired MockMvc; @MockBean TaskService02 service.
 *     2) not_found_404_problemdetail():
 *          when(service.find(99L)).thenThrow(new TaskNotFound02(99L));
 *          mockMvc.perform(get("/api/tasks/99"))
 *                 .andExpect(status().isNotFound())
 *                 .andExpect(content().contentType("application/problem+json"))
 *                 .andExpect(jsonPath("$.status").value(404))
 *                 .andExpect(jsonPath("$.detail", containsString("99")));
 *
 * ЦЕЛЬ: проверить единый контракт ошибок; не забыть @Import advice в срез.
 *
 * ВАЖНО: без @Import(GlobalAdvice02.class) срез @WebMvcTest не увидит advice → будет 500, не 404.
 *
 * ПОДСКАЗКА: ProblemDetail отдаётся с типом application/problem+json.
 */

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.*;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// --- ТЕСТ (каркас) ---
// TODO: @WebMvcTest(TaskController02.class)
// TODO: @Import(GlobalAdvice02.class)
class ErrorHandlingTest02 {

    // TODO: @Autowired MockMvc mockMvc;
    // TODO: @MockBean TaskService02 service;

    @Test
    void not_found_404_problemdetail() throws Exception {
        // TODO: when(service.find(99L)).thenThrow(new TaskNotFound02(99L));
        // TODO: mockMvc.perform(get("/api/tasks/99"))
        //              .andExpect(status().isNotFound())
        //              .andExpect(content().contentType("application/problem+json"))
        //              .andExpect(jsonPath("$.status").value(404))
        //              .andExpect(jsonPath("$.detail", containsString("99")));
    }
}
