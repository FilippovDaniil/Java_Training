/**
 * Задача 03 — Модуль 76: @WebMvcTest — проверка 404 (мок бросает исключение)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-web:3.2.x,
 *   org.springframework.boot:spring-boot-starter-test:3.2.x (см. theory.md).
 *
 * Это ТЕСТ-КЛАСС (без main).
 *
 * ЗАДАНИЕ:
 *   Контроллер/сервис/обработчик готовы. Сервис при отсутствии задачи бросает
 *   TaskNotFoundException03, глобальный обработчик превращает её в 404.
 *   1) Класс NotFoundTest03 пометьте @WebMvcTest(TaskController03.class).
 *      ВНИМАНИЕ: чтобы подхватился @RestControllerAdvice, импортируйте его:
 *      @Import(GlobalHandler03.class).
 *   2) @Autowired MockMvc; @MockBean TaskService03.
 *   3) В тесте returns404():
 *        - when(service.find(999L)).thenThrow(new TaskNotFoundException03(999L));
 *        - mockMvc.perform(get("/api/tasks/999")).andExpect(status().isNotFound());
 *
 * ЦЕЛЬ: проверить, что ошибка маппится в правильный HTTP-статус.
 *
 * ПОДСКАЗКА: @MockBean настраивается через Mockito.when(...).thenThrow(...).
 */

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// --- ТЕСТ (каркас) ---
// TODO: @WebMvcTest(TaskController03.class)
// TODO: @Import(GlobalHandler03.class)
class NotFoundTest03 {

    // TODO: @Autowired MockMvc mockMvc;
    // TODO: @MockBean TaskService03 service;

    @Test
    void returns404() throws Exception {
        // TODO: when(service.find(999L)).thenThrow(new TaskNotFoundException03(999L));
        // TODO: mockMvc.perform(get("/api/tasks/999")).andExpect(status().isNotFound());
    }
}
