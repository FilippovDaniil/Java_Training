package m95_spring_security_session_cors.practice.task05;

/**
 * Задача 05 — Модуль 95: logout (завершение сессии, удаление cookie)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-web, spring-boot-starter-security (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   1) Session-based приложение (formLogin), alice/password.
 *   2) Настройте logout:
 *        http.logout(logout -> logout
 *            .logoutUrl("/api/auth/logout")        // POST сюда — завершить сессию
 *            .invalidateHttpSession(true)          // уничтожить сессию
 *            .deleteCookies("JSESSIONID")          // удалить cookie сессии
 *            .logoutSuccessHandler((req, res, auth) -> res.setStatus(200)));  // 200 вместо редиректа
 *   3) Эндпоинт GET /api/tasks (authenticated).
 *   4) Проверьте: после POST /api/auth/logout сессия завершена, повторный /api/tasks → требует входа.
 *
 * ЦЕЛЬ: корректно завершать сессию и чистить cookie (важно на общих устройствах).
 *
 * ПОДСКАЗКА: для REST logoutSuccessHandler возвращает статус-код вместо редиректа на /login.
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

public class Task05 {
    public static void main(String[] args) {
        SpringApplication.run(Task05.class, args);
    }
}
