package m95_spring_security_session_cors.practice.task03;

/**
 * Задача 03 — Модуль 95: CORS для браузерного фронтенда (SPA)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-web, spring-boot-starter-security (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   SPA на http://localhost:3000 ходит к API. Разрешите CORS.
 *   1) Бин CorsConfigurationSource:
 *        CorsConfiguration cfg = new CorsConfiguration();
 *        cfg.setAllowedOrigins(List.of("http://localhost:3000"));  // КОНКРЕТНЫЙ origin
 *        cfg.setAllowedMethods(List.of("GET","POST","PUT","DELETE","OPTIONS"));
 *        cfg.setAllowedHeaders(List.of("*"));
 *        cfg.setAllowCredentials(true);
 *        UrlBasedCorsConfigurationSource src = new UrlBasedCorsConfigurationSource();
 *        src.registerCorsConfiguration("/**", cfg);
 *   2) В SecurityFilterChain: http.cors(Customizer.withDefaults()) — подхватит бин;
 *      всё authenticated, httpBasic, csrf.disable.
 *   3) В комментарии: почему "*" + allowCredentials(true) — недопустимо.
 *
 * ЦЕЛЬ: разрешить кросс-доменные запросы из конкретного фронтенда безопасно.
 *
 * ПОДСКАЗКА: http.cors() ДОЛЖЕН стоять так, чтобы preflight OPTSIONS не блокировался
 *            авторизацией; бин CorsConfigurationSource подхватывается автоматически.
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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.List;

public class Task03 {
    public static void main(String[] args) {
        SpringApplication.run(Task03.class, args);
    }
}
