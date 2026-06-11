package m97_spring_security_authorization.practice.task01;

/**
 * Задача 01 — Модуль 97: request-level правила по HTTP-методу
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-web, spring-boot-starter-security (см. theory.md). bare-javac не компилируется.
 *
 * ЗАДАНИЕ:
 *   Настройте разные права для разных методов одного ресурса /api/tasks:
 *     - GET    → hasAnyRole("USER","ADMIN")   (читать — любой вошедший);
 *     - POST   → hasAnyRole("USER","ADMIN")   (создавать — любой);
 *     - DELETE → hasRole("ADMIN")             (удалять — только ADMIN);
 *     - "/api/admin/**" → hasRole("ADMIN"); остальное authenticated.
 *   1) Используйте requestMatchers(HttpMethod.X, "путь").
 *   2) Пользователи: alice(USER), admin(ADMIN, USER) — in-memory, BCrypt.
 *   3) httpBasic, csrf.disable.
 *   4) Проверьте: alice GET 200, alice DELETE 403, admin DELETE 200.
 *
 * ЦЕЛЬ: освоить method-specific request-level правила.
 *
 * ПОДСКАЗКА: правила для конкретных методов — ПЕРЕД общими; anyRequest() — последним.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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

public class Task01 {
    public static void main(String[] args) {
        SpringApplication.run(Task01.class, args);
    }
}
