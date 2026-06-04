/**
 * Задача 07 — Модуль 93: МИНИ-ПРОЕКТ «Базовая защита Task Tracker API»
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-web, spring-boot-starter-security (см. theory.md).
 *
 * ЦЕЛЬ: заложить security-каркас сквозного проекта Task Tracker API — публичные служебные
 *       эндпоинты открыты, бизнес-API закрыто, есть способ узнать текущего пользователя.
 *       Этот каркас будем развивать в модулях 94–100 (in-memory → БД → JWT).
 *
 * ЗАДАНИЕ (всё в одном файле):
 *
 *   1) SecurityFilterChain07:
 *        - permitAll: "/actuator/health", "/api/auth/**" (вход/регистрация появятся позже);
 *        - authenticated: "/api/**" (задачи);
 *        - httpBasic (временный способ входа), csrf.disable (REST, без сессионных форм).
 *
 *   2) Контроллеры:
 *        - HealthController07: GET /actuator/health → "UP" (публично);
 *        - TaskController07: GET /api/tasks → список задач ТЕКУЩЕГО пользователя
 *          (используйте Principal: верните "<user>: [задачи]");
 *          POST /api/tasks (body — название) → "создано пользователем <user>".
 *
 *   3) GET /api/auth/info → публичная подсказка "POST /api/auth/login (появится в модуле 96)".
 *
 * АРХИТЕКТУРА (точка роста на весь блок):
 *   /actuator/health, /api/auth/** → permitAll
 *   /api/**                        → authenticated (Principal = текущий пользователь)
 *   вход: httpBasic (94 — in-memory users, 96 — БД, 98 — JWT)
 *
 * ОЖИДАЕМЫЙ ИТОГ:
 *   - GET /actuator/health и /api/auth/info → 200 без входа;
 *   - GET /api/tasks без входа → 401; с Basic-кредами → 200 и имя пользователя в ответе.
 *
 * ПОДСКАЗКА: соберите Task03 (path-правила), Task05 (Principal). Это фундамент —
 *            в следующих модулях заменим httpBasic на полноценную аутентификацию.
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@SpringBootApplication
public class Task07 {
    public static void main(String[] args) {
        SpringApplication.run(Task07.class, args);
    }
}

@Configuration
@EnableWebSecurity
class SecurityConfig07 {
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // TODO: http.authorizeHttpRequests(auth -> auth
        // TODO:     .requestMatchers("/actuator/health", "/api/auth/**").permitAll()
        // TODO:     .requestMatchers("/api/**").authenticated()
        // TODO:     .anyRequest().authenticated());
        // TODO: http.httpBasic(Customizer.withDefaults());
        // TODO: http.csrf(csrf -> csrf.disable());
        return http.build();
    }
}

@RestController
class HealthController07 {
    @GetMapping("/actuator/health")
    String health() { return "UP"; }
}

@RestController
@RequestMapping("/api/auth")
class AuthInfoController07 {
    @GetMapping("/info")
    String info() { return "POST /api/auth/login (появится в модуле 96)"; }
}

@RestController
@RequestMapping("/api/tasks")
class TaskController07 {
    @GetMapping
    String myTasks(Principal principal) {
        // TODO: return principal.getName() + ": " + List.of("Задача 1", "Задача 2");
        return null;
    }

    @PostMapping
    String create(@RequestBody String title, Principal principal) {
        // TODO: return "создано пользователем " + principal.getName() + ": " + title;
        return null;
    }
}
