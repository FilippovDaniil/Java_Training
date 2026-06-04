/**
 * Задача 01 — Модуль 100: @WithMockUser → 200, аноним → 401
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-web, spring-boot-starter-security, spring-boot-starter-test,
 *   spring-security-test.
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЗАДАНИЕ:
 *   Контроллер и конфиг готовы (ниже: всё /api/** требует аутентификации). Напишите тесты:
 *     1) Класс пометьте @WebMvcTest(TaskController01.class) и @Import(SecurityConfig01.class).
 *     2) @Autowired MockMvc.
 *     3) anonymous_gets_401(): get("/api/tasks") → status().isUnauthorized().
 *     4) authenticated_gets_200(): пометьте метод @WithMockUser → get("/api/tasks") → status().isOk().
 *
 * ЦЕЛЬ: усвоить базу — без аутентификации 401, c @WithMockUser 200.
 *
 * ВАЖНО: @Import(SecurityConfig01.class) обязателен — иначе @WebMvcTest применит дефолтную
 *        автоконфигурацию, и правила не совпадут с реальными.
 *
 * ПОДСКАЗКА: @WithMockUser подставляет фиктивного пользователя на весь тест-метод.
 */

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// --- ТЕСТ (каркас) ---
// TODO: @WebMvcTest(TaskController01.class)
// TODO: @Import(SecurityConfig01.class)
class TaskSecurityTest01 {

    // TODO: @Autowired MockMvc mockMvc;

    @Test
    void anonymous_gets_401() throws Exception {
        // TODO: mockMvc.perform(get("/api/tasks")).andExpect(status().isUnauthorized());
    }

    @Test
    // TODO: @WithMockUser
    void authenticated_gets_200() throws Exception {
        // TODO: mockMvc.perform(get("/api/tasks")).andExpect(status().isOk());
    }
}
