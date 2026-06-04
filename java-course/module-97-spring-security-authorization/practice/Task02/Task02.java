/**
 * Задача 02 — Модуль 97: method security — @EnableMethodSecurity + @PreAuthorize
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-web, spring-boot-starter-security (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   1) Включите method security: @EnableMethodSecurity на конфигурации.
 *   2) В TaskService02:
 *        @PreAuthorize("hasRole('ADMIN')")  void deleteTask(Long id) {...}
 *        @PreAuthorize("isAuthenticated()") List<String> myTasks() {...}
 *   3) Контроллер делегирует в сервис; правила доступа теперь на МЕТОДАХ, не на URL.
 *      В SecurityFilterChain достаточно anyRequest().authenticated() + httpBasic + csrf.disable.
 *   4) Пользователи alice(USER), admin(ADMIN). Проверьте: alice → deleteTask 403, admin → 200.
 *
 * ЦЕЛЬ: перенести проверку прав на уровень методов сервиса (ближе к бизнес-логике).
 *
 * ПОДСКАЗКА: без @EnableMethodSecurity аннотации @PreAuthorize молча игнорируются!
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import java.util.List;

public class Task02 {
    public static void main(String[] args) {
        SpringApplication.run(Task02.class, args);
    }
}
