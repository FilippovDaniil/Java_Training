/**
 * Задача 07 — Модуль 94: МИНИ-ПРОЕКТ «Task Tracker с in-memory пользователями»
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-web, spring-boot-starter-security (см. theory.md).
 *
 * ЦЕЛЬ: расширить базовый каркас (модуль 93) реальными пользователями в памяти,
 *       BCrypt-паролями и ролевым доступом. Источник пользователей позже заменим на БД (модуль 96).
 *
 * ЗАДАНИЕ (всё в одном файле):
 *
 *   1) PasswordEncoder = BCryptPasswordEncoder.
 *
 *   2) UserDetailsService (InMemoryUserDetailsManager):
 *        - alice / password → roles("USER");
 *        - admin / admin    → roles("ADMIN", "USER").
 *
 *   3) SecurityFilterChain07:
 *        - permitAll: "/actuator/health";
 *        - hasRole("ADMIN"): "/api/admin/**";
 *        - authenticated: "/api/**";
 *        - httpBasic; csrf.disable (stateless REST).
 *
 *   4) Контроллеры Task Tracker:
 *        - GET  /actuator/health → "UP" (публично);
 *        - GET  /api/tasks (Principal) → "<user>: список задач";
 *        - POST /api/tasks (Principal + body) → "создано <user>: <title>";
 *        - GET  /api/admin/users → "alice, admin" (только ADMIN).
 *
 * АРХИТЕКТУРА:
 *   InMemoryUserDetailsManager (alice USER, admin ADMIN) + BCrypt
 *        │
 *   SecurityFilterChain: health public · /api/admin/** ADMIN · /api/** authenticated · httpBasic
 *
 * ОЖИДАЕМЫЙ ИТОГ:
 *   - health публично; alice → /api/tasks 200, /api/admin/users 403; admin → всё 200;
 *   - в ответах задач фигурирует имя текущего пользователя (Principal).
 *
 * ПОДСКАЗКА: соберите Task01 (in-memory + BCrypt), Task03 (роли+пути), Task05 (csrf для REST).
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
    PasswordEncoder passwordEncoder() {
        // TODO: return new BCryptPasswordEncoder();
        return null;
    }

    @Bean
    UserDetailsService users(PasswordEncoder enc) {
        // TODO: var alice = User.withUsername("alice").password(enc.encode("password")).roles("USER").build();
        // TODO: var admin = User.withUsername("admin").password(enc.encode("admin")).roles("ADMIN", "USER").build();
        // TODO: return new InMemoryUserDetailsManager(alice, admin);
        return null;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // TODO: http.authorizeHttpRequests(auth -> auth
        // TODO:     .requestMatchers("/actuator/health").permitAll()
        // TODO:     .requestMatchers("/api/admin/**").hasRole("ADMIN")
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
@RequestMapping("/api/tasks")
class TaskController07 {
    @GetMapping
    String myTasks(Principal principal) {
        // TODO: return principal.getName() + ": " + List.of("Задача 1", "Задача 2");
        return null;
    }

    @PostMapping
    String create(@RequestBody String title, Principal principal) {
        // TODO: return "создано " + principal.getName() + ": " + title;
        return null;
    }
}

@RestController
@RequestMapping("/api/admin")
class AdminController07 {
    @GetMapping("/users")
    String users() { return "alice, admin"; }
}
