package m94_spring_security_inmemory.practice.task07;

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

public class Task07 {
    public static void main(String[] args) {
        SpringApplication.run(Task07.class, args);
    }
}
