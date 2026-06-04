/**
 * Задача 02 — Модуль 110: ролевой доступ в полном контексте (ADMIN 200 / USER 403)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-web, spring-boot-starter-security, spring-boot-starter-test, spring-security-test.
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЗАДАНИЕ:
 *   /api/admin/** доступен только ADMIN (правило в реальном конфиге). Проверьте в полном контексте:
 *     1) @SpringBootTest(classes = Task02.class) + @AutoConfigureMockMvc; @Autowired MockMvc.
 *     2) admin_200(): @WithMockUser(roles="ADMIN") → get("/api/admin/stats") → isOk().
 *     3) user_403(): @WithMockUser(roles="USER") → get("/api/admin/stats") → isForbidden().
 *     4) anon_401(): без аннотации → isUnauthorized().
 *
 * ЦЕЛЬ: подтвердить, что ролевые правила работают в интеграции со всем приложением.
 *
 * ВАЖНО: 401 (не аутентифицирован) ≠ 403 (нет роли) — проверяем оба.
 *
 * ПОДСКАЗКА: roles="ADMIN" → authority ROLE_ADMIN (без префикса в аннотации).
 */
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
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

@SpringBootApplication
public class Task02 {
    public static void main(String[] args) { SpringApplication.run(Task02.class, args); }

    @Bean
    SecurityFilterChain chain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(a -> a
                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated())
            .csrf(c -> c.disable());
        return http.build();
    }
}

@RestController
@RequestMapping("/api/admin")
class AdminController02 {
    @GetMapping("/stats")
    String stats() { return "users=2"; }
}

// --- ТЕСТ (каркас) ---
// TODO: @org.springframework.boot.test.context.SpringBootTest(classes = Task02.class)
// TODO: @org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
class RoleIntegrationTest02 {

    // TODO: @Autowired MockMvc mockMvc;

    @Test
    // TODO: @WithMockUser(roles = "ADMIN")
    void admin_200() throws Exception {
        // TODO: mockMvc.perform(get("/api/admin/stats")).andExpect(status().isOk());
    }

    @Test
    // TODO: @WithMockUser(roles = "USER")
    void user_403() throws Exception {
        // TODO: mockMvc.perform(get("/api/admin/stats")).andExpect(status().isForbidden());
    }

    @Test
    void anon_401() throws Exception {
        // TODO: mockMvc.perform(get("/api/admin/stats")).andExpect(status().isUnauthorized());
    }
}
