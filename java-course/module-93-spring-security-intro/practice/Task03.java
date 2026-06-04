/**
 * Задача 03 — Модуль 93: правила по путям (permitAll + authenticated) и их порядок
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-web, spring-boot-starter-security (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Настройте смешанные правила доступа:
 *     - /public/** и /actuator/health → permitAll (без входа);
 *     - всё остальное (/api/**) → authenticated.
 *   1) В authorizeHttpRequests расставьте правила в ПРАВИЛЬНОМ порядке:
 *        .requestMatchers("/public/**", "/actuator/health").permitAll()
 *        .anyRequest().authenticated()
 *      (частные правила — ПЕРЕД anyRequest; первое совпадение выигрывает).
 *   2) Добавьте httpBasic и csrf.disable.
 *   3) Проверьте: GET /public/info → 200 без входа; GET /api/tasks → 401 без входа.
 *
 * ЦЕЛЬ: освоить path-based правила и понять важность их порядка.
 *
 * ПОДСКАЗКА: если поставить anyRequest().authenticated() ПЕРВЫМ — публичные пути тоже
 *            потребуют входа (правило-перехватчик). anyRequest всегда последним.
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
import org.springframework.web.bind.annotation.RestController;

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
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // TODO: http.authorizeHttpRequests(auth -> auth
        // TODO:     .requestMatchers("/public/**", "/actuator/health").permitAll()
        // TODO:     .anyRequest().authenticated());
        // TODO: http.httpBasic(Customizer.withDefaults());
        // TODO: http.csrf(csrf -> csrf.disable());
        return http.build();
    }
}

@RestController
class MixedController03 {
    @GetMapping("/public/info")
    String info() { return "Публичная информация — доступна всем"; }

    @GetMapping("/api/tasks")
    List<String> tasks() { return List.of("Задача 1", "Задача 2"); }
}
