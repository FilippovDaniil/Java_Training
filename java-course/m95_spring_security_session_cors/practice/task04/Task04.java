package m95_spring_security_session_cors.practice.task04;

/**
 * Задача 04 — Модуль 95: безопасные cookies сессии (HttpOnly, Secure, SameSite)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-web, spring-boot-starter-security (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Защитите cookie сессии (JSESSIONID).
 *   1) В application.properties добавьте (впишите в SETTINGS-блок ниже как образец):
 *        server.servlet.session.cookie.http-only=true   # JS не читает cookie (анти-XSS)
 *        server.servlet.session.cookie.secure=true       # только HTTPS
 *        server.servlet.session.cookie.same-site=strict  # не отправлять кросс-сайтово (анти-CSRF)
 *   2) Приложение session-based (formLogin), пользователь alice/password.
 *   3) В CommandLineRunner напечатайте напоминание о настройках cookie (SETTINGS-блок).
 *   4) В комментарии: что даёт каждый атрибут (HttpOnly/Secure/SameSite).
 *
 * ЦЕЛЬ: понять атрибуты безопасности cookie и где они задаются.
 *
 * ПОДСКАЗКА: secure=true означает, что в dev по http cookie может не ставиться — для локали
 *            иногда временно secure=false; в проде ВСЕГДА true (за HTTPS).
 */

import org.springframework.boot.CommandLineRunner;
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

public class Task04 {
    public static void main(String[] args) {
        SpringApplication.run(Task04.class, args);
    }

    @Bean
    CommandLineRunner reminder() {
        return args -> {
            String settings = """
                    # application.properties — безопасность cookie сессии:
                    # TODO:
                    # server.servlet.session.cookie.http-only=true
                    # server.servlet.session.cookie.secure=true
                    # server.servlet.session.cookie.same-site=strict
                    """;
            System.out.println(settings);
        };
    }
}
