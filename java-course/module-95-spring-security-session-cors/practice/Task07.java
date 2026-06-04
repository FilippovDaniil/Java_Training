/**
 * Задача 07 — Модуль 95: МИНИ-ПРОЕКТ «Task Tracker API для SPA: Basic + CORS + загрузка»
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-web, spring-boot-starter-security (см. theory.md).
 *
 * ЦЕЛЬ: подготовить Task Tracker API к работе с браузерным фронтендом (SPA):
 *       HTTP Basic для входа, CORS для домена SPA, безопасные cookie, защищённая загрузка вложений.
 *
 * ЗАДАНИЕ (всё в одном файле):
 *
 *   1) Пользователь alice/password (in-memory, BCrypt).
 *
 *   2) CORS: CorsConfigurationSource — origin http://localhost:3000, методы GET/POST/PUT/DELETE/OPTIONS,
 *      заголовки *, allowCredentials(true).
 *
 *   3) SecurityFilterChain07:
 *        - http.cors(withDefaults());
 *        - permitAll: "/actuator/health";
 *        - authenticated: "/api/**";
 *        - httpBasic; csrf.disable (REST).
 *
 *   4) application.properties (в SETTINGS-блоке JavaDoc как образец):
 *        spring.servlet.multipart.max-file-size=5MB
 *        spring.servlet.multipart.max-request-size=10MB
 *        server.servlet.session.cookie.http-only=true
 *        server.servlet.session.cookie.same-site=strict
 *
 *   5) Контроллеры:
 *        - GET  /actuator/health → "UP" (публично);
 *        - GET  /api/tasks (Principal) → "<user>: задачи";
 *        - POST /api/tasks/{id}/attachments (MultipartFile) → безопасная загрузка
 *          (белый список типов, UUID-имя; как в Task06).
 *
 * АРХИТЕКТУРА:
 *   SPA(localhost:3000) ──CORS──► API ──HTTP Basic──► /api/** (authenticated)
 *        вложения: лимиты + белый список типов + UUID-имя (анти path-traversal)
 *        cookie: HttpOnly + SameSite
 *
 * ОЖИДАЕМЫЙ ИТОГ:
 *   - preflight OPTIONS с localhost:3000 проходит; health публичен; /api/tasks требует Basic;
 *   - загрузка валидного типа → ок, недопустимый тип → 400.
 *
 * ПОДСКАЗКА: соберите Task01 (Basic), Task03 (CORS), Task04 (cookie), Task06 (загрузка).
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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.UUID;

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
    PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

    @Bean
    UserDetailsService users(PasswordEncoder enc) {
        return new InMemoryUserDetailsManager(
                User.withUsername("alice").password(enc.encode("password")).roles("USER").build());
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        // TODO: CorsConfiguration cfg = new CorsConfiguration();
        // TODO: cfg.setAllowedOrigins(List.of("http://localhost:3000"));
        // TODO: cfg.setAllowedMethods(List.of("GET","POST","PUT","DELETE","OPTIONS"));
        // TODO: cfg.setAllowedHeaders(List.of("*"));
        // TODO: cfg.setAllowCredentials(true);
        // TODO: var src = new UrlBasedCorsConfigurationSource(); src.registerCorsConfiguration("/**", cfg);
        // TODO: return src;
        return null;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // TODO: http.cors(Customizer.withDefaults());
        // TODO: http.authorizeHttpRequests(auth -> auth
        // TODO:     .requestMatchers("/actuator/health").permitAll()
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
    private static final Set<String> ALLOWED = Set.of("image/png", "image/jpeg", "application/pdf");

    @GetMapping
    String myTasks(Principal principal) {
        // TODO: return principal.getName() + ": " + List.of("Задача 1", "Задача 2");
        return null;
    }

    @PostMapping("/{id}/attachments")
    String upload(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        // TODO: проверить непустоту + ALLOWED.contains(file.getContentType());
        // TODO: String safeName = UUID.randomUUID() + ".bin";   // не из оригинального имени
        // TODO: return "к задаче " + id + " загружено " + safeName;
        return null;
    }
}
