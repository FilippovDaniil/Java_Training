/**
 * Задача 01 — Модуль 93: первый SecurityFilterChain (permitAll)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-web, spring-boot-starter-security (см. theory.md).
 *   bare-javac НЕ компилируется — это норма для Spring-задач.
 *
 * ЗАДАНИЕ:
 *   1) После подключения starter-security ВСЁ закрыто по умолчанию (401).
 *      Объявите бин SecurityFilterChain, который временно ОТКРЫВАЕТ всё:
 *        http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
 *        http.csrf(csrf -> csrf.disable());   // для простоты REST-проверки
 *        return http.build();
 *   2) Контроллер TaskController01 отдаёт список задач на GET /api/tasks.
 *   3) Убедитесь: запрос проходит до контроллера (200), т.к. ваш бин заменил дефолт.
 *
 * ЦЕЛЬ: понять, что бин SecurityFilterChain ЗАМЕНЯЕТ поведение по умолчанию.
 *
 * ПОДСКАЗКА: один бин SecurityFilterChain полностью определяет правила цепочки.
 *            permitAll временно — в следующих задачах закроем доступ осознанно.
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
public class Task01 {
    public static void main(String[] args) {
        SpringApplication.run(Task01.class, args);
    }
}

@Configuration
@EnableWebSecurity
class SecurityConfig01 {
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // TODO: http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
        // TODO: http.csrf(csrf -> csrf.disable());
        // TODO: return http.build();
        return http.build();
    }
}

@RestController
@RequestMapping("/api/tasks")
class TaskController01 {
    @GetMapping
    List<String> list() {
        return List.of("Купить молоко", "Написать отчёт");
    }
}
