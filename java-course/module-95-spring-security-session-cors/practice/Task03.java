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

@SpringBootApplication
public class Task03 {
    public static void main(String[] args) {
        SpringApplication.run(Task03.class, args);
    }
}

@Configuration
@EnableWebSecurity
class SecurityConfig03 {
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
        // TODO: cfg.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        // TODO: cfg.setAllowedHeaders(List.of("*"));
        // TODO: cfg.setAllowCredentials(true);
        // TODO: UrlBasedCorsConfigurationSource src = new UrlBasedCorsConfigurationSource();
        // TODO: src.registerCorsConfiguration("/**", cfg);
        // TODO: return src;
        return null;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // TODO: http.cors(Customizer.withDefaults());
        // TODO: http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
        // TODO: http.httpBasic(Customizer.withDefaults());
        // TODO: http.csrf(csrf -> csrf.disable());
        return http.build();
    }
}

@RestController
class TaskController03 {
    @GetMapping("/api/tasks")
    String tasks() { return "Задачи (доступно SPA с localhost:3000)"; }
}
