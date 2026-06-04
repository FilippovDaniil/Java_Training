/**
 * Задача 04 — Модуль 94: форма логина (formLogin) для браузерного входа
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-web, spring-boot-starter-security (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   1) Пользователь alice/password (in-memory) + BCrypt.
 *   2) SecurityFilterChain с формой логина:
 *        - permitAll: "/login", "/public/**";
 *        - authenticated: всё остальное;
 *        - http.formLogin(form -> form.defaultSuccessUrl("/api/tasks", true));
 *        - http.logout(Customizer.withDefaults());
 *      (CSRF НЕ отключаем — это session-based вход через форму, токен нужен; см. Task05.)
 *   3) Контроллер GET /api/tasks → список; GET /public/home → публичная страница.
 *   4) Проверьте в браузере: открытие /api/tasks без входа → редирект на форму /login;
 *      после входа alice/password → /api/tasks.
 *
 * ЦЕЛЬ: освоить формовый вход для браузерных (session-based) приложений.
 *
 * ПОДСКАЗКА: без loginPage(...) Spring сгенерирует страницу логина автоматически.
 *            formLogin делает приложение session-based (JSESSIONID) — это НЕ stateless REST.
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class Task04 {
    public static void main(String[] args) {
        SpringApplication.run(Task04.class, args);
    }
}

@Configuration
@EnableWebSecurity
class SecurityConfig04 {
    @Bean
    PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

    @Bean
    UserDetailsService users(PasswordEncoder enc) {
        return new InMemoryUserDetailsManager(
                User.withUsername("alice").password(enc.encode("password")).roles("USER").build());
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // TODO: http.authorizeHttpRequests(auth -> auth
        // TODO:     .requestMatchers("/login", "/public/**").permitAll()
        // TODO:     .anyRequest().authenticated());
        // TODO: http.formLogin(form -> form.defaultSuccessUrl("/api/tasks", true));
        // TODO: http.logout(Customizer.withDefaults());
        return http.build();
    }
}

@RestController
class Controllers04 {
    @GetMapping("/public/home")
    String home() { return "Публичная главная"; }

    @GetMapping("/api/tasks")
    String tasks() { return "Задачи (после входа через форму)"; }
}
