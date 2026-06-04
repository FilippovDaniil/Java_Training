/**
 * Задача 02 — Модуль 100: проверка ролей (ADMIN → 200, USER → 403)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-web, spring-boot-starter-security, spring-boot-starter-test,
 *   spring-security-test.
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЗАДАНИЕ:
 *   Эндпоинт /api/admin/stats доступен только роли ADMIN (конфиг готов). Напишите тесты:
 *     1) @WebMvcTest(AdminController02.class) + @Import(SecurityConfig02.class), @Autowired MockMvc.
 *     2) admin_gets_200(): @WithMockUser(roles = "ADMIN") → get("/api/admin/stats") → isOk().
 *     3) user_gets_403():  @WithMockUser(roles = "USER")  → get("/api/admin/stats") → isForbidden().
 *     4) anonymous_gets_401(): без аннотации → isUnauthorized().
 *
 * ЦЕЛЬ: различать 401 (не аутентифицирован) и 403 (аутентифицирован, но нет прав).
 *
 * ВАЖНО: roles = "ADMIN" → authority ROLE_ADMIN. НЕ писать roles="ROLE_ADMIN" (будет ROLE_ROLE_ADMIN).
 *
 * ПОДСКАЗКА: 401 = «ты кто?», 403 = «тебе сюда нельзя».
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RestController
@RequestMapping("/api/admin")
class AdminController02 {
    @GetMapping("/stats")
    String stats() { return "users=2"; }
}

@Configuration
@EnableWebSecurity
class SecurityConfig02 {
    @Bean
    SecurityFilterChain chain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(a -> a
                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated())
            .csrf(c -> c.disable());
        return http.build();
    }
}

// TODO: @WebMvcTest(AdminController02.class)
// TODO: @Import(SecurityConfig02.class)
class AdminSecurityTest02 {

    // TODO: @Autowired MockMvc mockMvc;

    @Test
    // TODO: @WithMockUser(roles = "ADMIN")
    void admin_gets_200() throws Exception {
        // TODO: mockMvc.perform(get("/api/admin/stats")).andExpect(status().isOk());
    }

    @Test
    // TODO: @WithMockUser(roles = "USER")
    void user_gets_403() throws Exception {
        // TODO: mockMvc.perform(get("/api/admin/stats")).andExpect(status().isForbidden());
    }

    @Test
    void anonymous_gets_401() throws Exception {
        // TODO: mockMvc.perform(get("/api/admin/stats")).andExpect(status().isUnauthorized());
    }
}
