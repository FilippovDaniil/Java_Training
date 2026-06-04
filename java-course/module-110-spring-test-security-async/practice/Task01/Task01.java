/**
 * Задача 01 — Модуль 110: security в полном контексте (@SpringBootTest + @WithMockUser)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-web, spring-boot-starter-security, spring-boot-starter-test, spring-security-test.
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЗАДАНИЕ:
 *   В отличие от среза @WebMvcTest (модуль 100), здесь поднимается ВСЁ приложение —
 *   security-цепочка реальная. Проверьте интеграцию:
 *     1) @SpringBootTest(classes = Task01.class) + @AutoConfigureMockMvc; @Autowired MockMvc.
 *     2) anonymous_401(): get("/api/tasks") → status().isUnauthorized().
 *     3) user_200(): @WithMockUser → get("/api/tasks") → status().isOk().
 *
 * ЦЕЛЬ: тестировать security во взаимодействии со всем стеком (а не в изоляции среза).
 *
 * ВАЖНО: @AutoConfigureMockMvc обязателен, чтобы получить MockMvc в @SpringBootTest.
 *
 * ПОДСКАЗКА: те же @WithMockUser/post-processors, что в модуле 100, но контекст полный.
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
import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class Task01 {
    public static void main(String[] args) { SpringApplication.run(Task01.class, args); }

    @Bean
    SecurityFilterChain chain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(a -> a.anyRequest().authenticated()).csrf(c -> c.disable());
        return http.build();
    }
}
