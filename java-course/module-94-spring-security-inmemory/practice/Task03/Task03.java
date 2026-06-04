/**
 * Задача 03 — Модуль 94: несколько пользователей с ролями (USER/ADMIN)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-web, spring-boot-starter-security (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   1) InMemoryUserDetailsManager с двумя пользователями:
 *        - alice / password → roles("USER");
 *        - admin / admin    → roles("ADMIN", "USER").
 *   2) SecurityFilterChain с правилами по ролям:
 *        - "/api/admin/**" → hasRole("ADMIN");
 *        - "/api/**"       → authenticated;
 *        httpBasic, csrf.disable.
 *   3) Контроллеры: GET /api/tasks (любой вошедший), GET /api/admin/stats (только ADMIN).
 *   4) Проверьте: alice → /api/tasks 200, /api/admin/stats 403; admin → оба 200.
 *
 * ЦЕЛЬ: связать роли пользователей с правилами доступа по путям.
 *
 * ПОДСКАЗКА: roles("USER") = authority ROLE_USER; hasRole("USER") добавит ROLE_ сам.
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

public class Task03 {
    public static void main(String[] args) {
        SpringApplication.run(Task03.class, args);
    }
}
