/**
 * Задача 02 — Модуль 93: закрыть всё (anyRequest().authenticated())
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-web, spring-boot-starter-security (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   1) Настройте SecurityFilterChain так, чтобы ЛЮБОЙ запрос требовал аутентификации:
 *        http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
 *        http.httpBasic(Customizer.withDefaults());   // чтобы REST-клиент мог войти
 *        http.csrf(csrf -> csrf.disable());
 *   2) Контроллер отдаёт /api/tasks.
 *   3) Проверьте поведение:
 *        - без заголовка Authorization → 401 Unauthorized;
 *        - с Basic-кредами пользователя по умолчанию (user + пароль из лога) → 200.
 *
 * ЦЕЛЬ: увидеть «secure by default» осознанно — без входа доступа нет.
 *
 * ПОДСКАЗКА: при старте Spring Boot печатает «Using generated security password: ...».
 *            httpBasic нужен, чтобы curl/Postman могли передать логин-пароль.
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
public class Task02 {
    public static void main(String[] args) {
        SpringApplication.run(Task02.class, args);
    }
}

@Configuration
@EnableWebSecurity
class SecurityConfig02 {
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // TODO: http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
        // TODO: http.httpBasic(Customizer.withDefaults());
        // TODO: http.csrf(csrf -> csrf.disable());
        return http.build();
    }
}

@RestController
@RequestMapping("/api/tasks")
class TaskController02 {
    @GetMapping
    List<String> list() {
        return List.of("Купить молоко", "Написать отчёт");
    }
}
