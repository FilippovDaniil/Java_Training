package m100_spring_security_testing.practice.task06;

/**
 * Задача 06 — Модуль 100: hardening — проверка security-заголовков
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-web, spring-boot-starter-security, spring-boot-starter-test,
 *   spring-security-test.
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЗАДАНИЕ:
 *   Spring Security добавляет защитные заголовки по умолчанию. Зафиксируйте их тестом:
 *     1) @WebMvcTest(PingController06.class) + @Import(SecurityConfig06.class), @Autowired MockMvc.
 *     2) headers_present(): @WithMockUser → get("/api/ping"):
 *          - header().string("X-Content-Type-Options", "nosniff");
 *          - header().string("X-Frame-Options", "DENY");
 *          - header().string("Cache-Control", containsString("no-store")).
 *   Статические импорты header/containsString/status/get добавлены.
 *
 * ЦЕЛЬ: тест-«страховка» от регрессий безопасности — если кто-то ослабит заголовки, тест упадёт.
 *
 * ТАБЛИЦА ЗАГОЛОВКОВ:
 *   X-Content-Type-Options: nosniff   → запрет MIME-sniffing
 *   X-Frame-Options: DENY             → защита от clickjacking
 *   Cache-Control: no-store           → не кэшировать защищённые ответы
 *
 * ПОДСКАЗКА: тонкая настройка — http.headers(h -> h.frameOptions(...).contentSecurityPolicy(...)).
 */

import org.hamcrest.Matchers;
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
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// TODO: @WebMvcTest(PingController06.class)
// TODO: @Import(SecurityConfig06.class)
class HardeningTest06 {

    // TODO: @Autowired MockMvc mockMvc;

    @Test
    // TODO: @WithMockUser
    void headers_present() throws Exception {
        // TODO: mockMvc.perform(get("/api/ping"))
        //              .andExpect(status().isOk())
        //              .andExpect(header().string("X-Content-Type-Options", "nosniff"))
        //              .andExpect(header().string("X-Frame-Options", "DENY"))
        //              .andExpect(header().string("Cache-Control", containsString("no-store")));
    }
}
