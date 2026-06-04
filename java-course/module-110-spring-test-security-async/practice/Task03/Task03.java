/**
 * Задача 03 — Модуль 110: HTTP Basic в полном контексте (.with(httpBasic))
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-web, spring-boot-starter-security, spring-boot-starter-test, spring-security-test.
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЗАДАНИЕ:
 *   Приложение с httpBasic и реальным пользователем alice/password (готово). Проверьте аутентификацию
 *   реальными кредами через post-processor:
 *     1) @SpringBootTest(classes = Task03.class) + @AutoConfigureMockMvc; @Autowired MockMvc.
 *     2) valid_basic_200(): get("/api/tasks").with(httpBasic("alice","password")) → isOk().
 *     3) wrong_basic_401(): .with(httpBasic("alice","WRONG")) → isUnauthorized().
 *
 * ЦЕЛЬ: тестировать реальную проверку кредов (UserDetailsService + PasswordEncoder) в интеграции.
 *
 * ВАЖНО: в отличие от @WithMockUser (фиктивный юзер), httpBasic проверяет НАСТОЯЩИЕ креды через стек.
 *
 * ПОДСКАЗКА: статический импорт httpBasic уже добавлен (SecurityMockMvcRequestPostProcessors.*).
 */

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class Task03 {
    public static void main(String[] args) { SpringApplication.run(Task03.class, args); }

    @Bean PasswordEncoder encoder() { return new BCryptPasswordEncoder(); }

    @Bean
    UserDetailsService users(PasswordEncoder enc) {
        return new InMemoryUserDetailsManager(
                User.withUsername("alice").password(enc.encode("password")).roles("USER").build());
    }

    @Bean
    SecurityFilterChain chain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(a -> a.anyRequest().authenticated())
            .httpBasic(Customizer.withDefaults())
            .csrf(c -> c.disable());
        return http.build();
    }
}
