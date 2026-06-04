/**
 * Задача 03 — Модуль 100: HTTP Basic в тестах (.with(httpBasic(...)))
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-web, spring-boot-starter-security, spring-boot-starter-test,
 *   spring-security-test.
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЗАДАНИЕ:
 *   Конфиг включает httpBasic + in-memory пользователя alice/password (готов ниже).
 *   Напишите тесты, передавая креды post-processor'ом httpBasic:
 *     1) @WebMvcTest(TaskController03.class) + @Import(SecurityConfig03.class), @Autowired MockMvc.
 *     2) valid_basic_200(): get("/api/tasks").with(httpBasic("alice","password")) → isOk().
 *     3) wrong_password_401(): .with(httpBasic("alice","WRONG")) → isUnauthorized().
 *     4) no_credentials_401(): без .with(...) → isUnauthorized().
 *   Статический импорт httpBasic уже добавлен (SecurityMockMvcRequestPostProcessors.*).
 *
 * ЦЕЛЬ: тестировать HTTP Basic (модуль 95) без ручного кодирования заголовка Authorization.
 *
 * ВАЖНО: httpBasic(...) сам формирует "Authorization: Basic base64(user:pass)".
 *
 * ПОДСКАЗКА: post-processor .with(...) модифицирует КОНКРЕТНЫЙ запрос (в отличие от @WithMockUser).
 */
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.Customizer;
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

@RestController
@RequestMapping("/api/tasks")
class TaskController03 {
    @GetMapping
    List<String> list() { return List.of("Задача 1"); }
}

@Configuration
@EnableWebSecurity
class SecurityConfig03 {
    @Bean
    PasswordEncoder encoder() { return new BCryptPasswordEncoder(); }

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

// TODO: @WebMvcTest(TaskController03.class)
// TODO: @Import(SecurityConfig03.class)
class BasicAuthTest03 {

    // TODO: @Autowired MockMvc mockMvc;

    @Test
    void valid_basic_200() throws Exception {
        // TODO: mockMvc.perform(get("/api/tasks").with(httpBasic("alice", "password")))
        //              .andExpect(status().isOk());
    }

    @Test
    void wrong_password_401() throws Exception {
        // TODO: mockMvc.perform(get("/api/tasks").with(httpBasic("alice", "WRONG")))
        //              .andExpect(status().isUnauthorized());
    }

    @Test
    void no_credentials_401() throws Exception {
        // TODO: mockMvc.perform(get("/api/tasks")).andExpect(status().isUnauthorized());
    }
}
