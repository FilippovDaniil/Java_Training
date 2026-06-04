/**
 * Задача 02 — Модуль 95: управление сессиями (политика, fixation, конкурентность)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-web, spring-boot-starter-security (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Настройте session-based приложение с защитой сессий.
 *   1) Пользователь alice/password (in-memory), formLogin.
 *   2) http.sessionManagement(sm -> sm
 *          .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)   // создавать при необходимости
 *          .sessionFixation(sf -> sf.changeSessionId())                // анти-fixation
 *          .maximumSessions(1)                                         // 1 активная сессия на юзера
 *          .maxSessionsPreventsLogin(false));                          // новый вход вытесняет старый
 *   3) Эндпоинт GET /api/tasks (authenticated).
 *   4) В комментарии: чем session fixation опасна и как changeSessionId защищает.
 *
 * ЦЕЛЬ: освоить политику создания сессий и защиту от фиксации/конкурентных входов.
 *
 * ПОДСКАЗКА: для stateless REST (модуль 98) политика будет STATELESS — сессии не создаются вовсе.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

public class Task02 {
    public static void main(String[] args) {
        SpringApplication.run(Task02.class, args);
    }
}
