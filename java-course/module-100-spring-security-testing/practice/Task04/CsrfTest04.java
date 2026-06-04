/**
 * Задача 04 — Модуль 100: CSRF в тестах (.with(csrf()))
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-web, spring-boot-starter-security, spring-boot-starter-test,
 *   spring-security-test.
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЗАДАНИЕ:
 *   Конфиг — session-режим с ВКЛЮЧЁННЫМ CSRF (csrf по умолчанию, готов ниже).
 *   В таком режиме POST без CSRF-токена отклоняется (403). Напишите тесты:
 *     1) @WebMvcTest(TaskController04.class) + @Import(SecurityConfig04.class), @Autowired MockMvc.
 *     2) post_without_csrf_403(): @WithMockUser → post("/api/tasks") c JSON-телом, БЕЗ .with(csrf())
 *          → status().isForbidden().
 *     3) post_with_csrf_201(): @WithMockUser → тот же POST + .with(csrf())
 *          → status().isCreated().
 *   Статические импорты csrf/post/status добавлены.
 *
 * ЦЕЛЬ: понять, когда нужен .with(csrf()) — в session-формах CSRF включён (модуль 94);
 *       в чистом stateless REST (STATELESS + csrf.disable) он не нужен.
 *
 * ВАЖНО: 403 на POST в тесте — частый симптом «забыли .with(csrf())».
 *
 * ПОДСКАЗКА: GET/HEAD/OPTIONS CSRF не требуют — только изменяющие методы (POST/PUT/PATCH/DELETE).
 */

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// TODO: @WebMvcTest(TaskController04.class)
// TODO: @Import(SecurityConfig04.class)
class CsrfTest04 {

    // TODO: @Autowired MockMvc mockMvc;

    @Test
    // TODO: @WithMockUser
    void post_without_csrf_403() throws Exception {
        // TODO: mockMvc.perform(post("/api/tasks")
        //              .contentType(MediaType.APPLICATION_JSON).content("{\"title\":\"X\"}"))
        //              .andExpect(status().isForbidden());
    }

    @Test
    // TODO: @WithMockUser
    void post_with_csrf_201() throws Exception {
        // TODO: mockMvc.perform(post("/api/tasks").with(csrf())
        //              .contentType(MediaType.APPLICATION_JSON).content("{\"title\":\"X\"}"))
        //              .andExpect(status().isCreated());
    }
}
